package org.example

import createHttpClient
import filterBooksByAuthor
import filterBooksByRating
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("Application")

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val client = createHttpClient()

    routing {
        get("/parse") {
            try {
                val url = "https://www.litres.ru/popular/"
                val html = htmlContent(client, url)
                val books = parsingBook(html)
                insertBooks(books)
                call.respond(HttpStatusCode.OK, "Парсинг завершен и данные сохранены.")
            } catch (e: Exception) {
                logger.error("Ошибка при парсинге: ${e.message}", e)
                call.respond(HttpStatusCode.InternalServerError, "Ошибка при парсинге.")
            }
        }

        get("/books/author/{author}") {
            val author = call.parameters["author"] ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Параметр автора указан неправильно"
            )
            try {
                val books = getAllBooks()
                val filteredBooks = filterBooksByAuthor(books, author)
                call.respond(filteredBooks)
            } catch (e: Exception) {
                logger.error("Ошибка при получении книг по автору: ${e.message}", e)
                call.respond(HttpStatusCode.InternalServerError, "Ошибка при получении книг.")
            }
        }

        get("/books/rating/{rating}") {
            val rating = call.parameters["rating"]?.toDoubleOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Неправильный рейтинг"
            )
            try {
                val books = getAllBooks()
                val filteredBooks = filterBooksByRating(books, rating)
                call.respond(filteredBooks)
            } catch (e: Exception) {
                logger.error("Ошибка при получении книг по рейтингу: ${e.message}", e)
                call.respond(HttpStatusCode.InternalServerError, "Ошибка при получении книг.")
            }
        }
    }
}

fun getAllBooks(): List<Book> {
    return transaction {
        BooksTable.selectAll().map {
            Book(
                title = it[BooksTable.title],
                author = it[BooksTable.author],
                link = it[BooksTable.link],
                votes = it[BooksTable.votes],
                rating = it[BooksTable.rating]
            )
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}