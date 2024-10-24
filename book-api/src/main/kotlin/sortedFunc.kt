import org.example.Book


fun filterBooksByAuthor(books: List<Book>, author: String): List<Book> {
    return books.filter { it.author.equals(author, ignoreCase = true) }
}
fun filterBooksByRating(books: List<Book>, targetRating: Double): List<Book> {
    return books.filter { it.rating == targetRating }
}