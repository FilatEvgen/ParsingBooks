package org.example

import java.util.regex.Pattern


val books = mutableListOf<Book>()

fun parsingBook(html: String): List<Book> {
    val pattern = Pattern.compile(
        "<div class=\"ArtInfo_wrapper__GoMsb\">.*?<a data-testid=\"art__title\" href=\"(.*?)\".*?<p class=\"ArtInfo_title__h_5Ay\">(.*?)</p>.*?<a data-testid=\"art__authorName\".*?>(.*?)</a>.*?<div class=\"ArtRating_rating__ntve8\" data-testid=\"art__ratingAvg\">(.*?)</div>.*?<div class=\"ArtRating_votes__MIJS1\" data-testid=\"art__ratingCount\">(.*?)</div>",
        Pattern.DOTALL
    )
    val matcher = pattern.matcher(html)
    while (matcher.find()){
        val link = matcher.group(1)
        val title = matcher.group(2)
        val author = matcher.group(3)
        val votesString = matcher.group(5).replace(",", "")
        val votes = votesString.toInt()
        val ratingString = matcher.group(4).replace(",", ".")
        val rating = ratingString.toDouble()

        insertBook(title, author, link, votes, rating)
        books.add(Book(title, author, link, votes, rating))
        }
    return books
    }

