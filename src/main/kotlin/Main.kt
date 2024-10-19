package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.regex.Pattern


suspend fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "1234Evgen"
    )
    transaction {
        SchemaUtils.create(BooksTable)
    }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    val url = "https://www.litres.ru/popular/"
    val response = client.get(url)
    val html = response.bodyAsText()
    val pattern = Pattern.compile(
        "<div class=\"ArtInfo_wrapper__GoMsb\">.*?<a data-testid=\"art__title\" href=\"(.*?)\".*?<p class=\"ArtInfo_title__h_5Ay\">(.*?)</p>.*?<a data-testid=\"art__authorName\".*?>(.*?)</a>.*?<div class=\"ArtRating_rating__ntve8\" data-testid=\"art__ratingAvg\">(.*?)</div>.*?<div class=\"ArtRating_votes__MIJS1\" data-testid=\"art__ratingCount\">(.*?)</div>",
        Pattern.DOTALL
    )
    val matcher = pattern.matcher(html)

    while (matcher.find()) {
        val link = matcher.group(1)
        val title = matcher.group(2)
        val author = matcher.group(3)
        val rating = matcher.group(4).replace(",", ".").toDouble()
        val votes = matcher.group(5).toInt()

        transaction {
            BooksTable.insert {
                it[BooksTable.title] = title
                it[BooksTable.author] = author
                it[BooksTable.link] = link
                it[BooksTable.votes] = votes
                it[BooksTable.rating] = rating
            }
        }
    }
    println("Данные о книгах успешно записаны в базу данных")
}