package org.example

import java.util.regex.Pattern
import Book

val books = mutableListOf<Book>()

fun parsingBook(html: String): List<Book> {
    val pattern = Pattern.compile(
        "<div class=\"ArtInfo_wrapper__GoMsb\">.*?<a data-testid=\"art__title\" href=\"(.*?)\".*?<p class=\"ArtInfo_title__h_5Ay\">(.*?)</p>.*?<a data-testid=\"art__authorName\".*?>(.*?)</a>.*?<div class=\"ArtRating_rating__ntve8\" data-testid=\"art__ratingAvg\">(.*?)</div>.*?<div class=\"ArtRating_votes__MIJS1\" data-testid=\"art__ratingCount\">(.*?)</div>",
        Pattern.DOTALL
    )
    val matcher = pattern.matcher(html)
    while (matcher.find()){
        val title = matcher.group(1)
        val author = matcher.group(2)
        val link = matcher.group(3)
        val votes = matcher.group(4).toInt()
        val rating = matcher.group(5).replace(",", ".").toDouble()
        insertBook(title, author, link, votes, rating)
        books.add(Book(title, author, link, votes, rating))
        }
    return books
    }

