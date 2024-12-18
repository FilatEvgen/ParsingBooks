package org.example

import java.util.regex.Pattern

fun parseBooks(html: String): List<Book> {
    val pattern = Pattern.compile(
        "<div class=\"ArtInfo_wrapper__GoMsb\">.*?<a data-testid=\"art__title\" href=\"(.*?)\".*?<p class=\"ArtInfo_title__h_5Ay\">(.*?)</p>.*?<a data-testid=\"art__authorName\".*?>(.*?)</a>.*?<div class=\"ArtRating_rating__ntve8\" data-testid=\"art__ratingAvg\">(.*?)</div>.*?<div class=\"ArtRating_votes__MIJS1\" data-testid=\"art__ratingCount\">(.*?)</div>",
        Pattern.DOTALL
    )
    val matcher = pattern.matcher(html)
    val books = mutableListOf<Book>()
    while (matcher.find()) {
        val link = matcher.group(1)
        val title = matcher.group(2)
        val author = matcher.group(3)
        val votesString = matcher.group(5).replace(",", "")
        val votes = votesString.toIntOrNull()
        val ratingString = matcher.group(4).replace(",", ".")
        val rating = ratingString.toDoubleOrNull()
        if (votes != null && rating != null) {
        books.add(Book(title, author, link, votes, rating))
        }
    }
    return books
}