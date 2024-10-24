package org.example

import Book

fun filterBooksByAuthor(html: List<Book>, author: String): List<Book> {
    return html.filter { it.author == author }
}
fun filterBooksByRating(books: List<Book>, targetRating: Double): List<Book> {
    return books.filter { it.rating == targetRating }
}