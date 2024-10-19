package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import java.util.regex.Pattern

fun printBookDetails(books: List<BookModel>): String {
    return books.joinToString { it.toString() }
}
fun filterBooksByAuthor(books: List<BookModel>, author: String): List<BookModel> {
    return books.filter { it.author == author }
}
fun findLongestBook(books: List<BookModel>): BookModel?{
    return books.maxByOrNull { it.votes }
}
fun calculateAverageVotes(books: List<BookModel>): Double{
    return if (books.isEmpty()) {
        0.0
    } else {
        books.map {it.votes}.average()
    }
}
fun sortBooksByRating(books: List<BookModel>): List<BookModel>{
    return books.sortedBy { it.rating }
}

suspend fun main() {
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

    val books = mutableListOf<BookModel>()

    while (matcher.find()) {
        val link = matcher.group(1)
        val title = matcher.group(2)
        val author = matcher.group(3)
        val rating = matcher.group(4).replace(",", ".").toDouble()
        val votes = matcher.group(5).toInt()

        val book = BookModel(link, title, author, rating, votes)
        books.add(book)
    }
    val authorFilter = "Виктор Пелевин"
    val filteredBooks = filterBooksByAuthor(books, authorFilter)
    println("Книги автора $authorFilter:")
    println(printBookDetails(filteredBooks))

    val votesBook = findLongestBook(books)
    println("Самая длинная книга: $votesBook")

    val averageVotes = calculateAverageVotes(books)
    println("Среднее количество страниц: $averageVotes")

    val sortedBooks = sortBooksByRating(books)
    println("Книги отсортированы по рейтингу:")
    println(printBookDetails(sortedBooks))

}