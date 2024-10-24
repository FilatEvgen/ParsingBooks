package org.example

import filterBooksByAuthor
import filterBooksByRating
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import module

val configAddres = System.getenv("CONFIG_ADDRES")

suspend fun main() {
    val config = loadConfig(configAddres)
    connectToDatabase(config)
    val client = createHttpClient()
    val url = "https://www.litres.ru/popular/"
    val html = htmlContent(client, url)
    parsingBook(html)
    val author = "Виктор Пелевин"
    val filteredBooks = filterBooksByAuthor(books, author)
    val sortedBooks = filterBooksByRating(filteredBooks, 4.0)
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
    println("Данные о книгах успешно записаны в базу данных")
}