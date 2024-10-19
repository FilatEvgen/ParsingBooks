package org.example


data class BookModel(
    val link: String,
    val title: String,
    val author: String,
    val rating: Double,
    val votes: Int
) {
    override fun toString(): String {
        return "$link, $title, $author, $rating, $votes"
    }
}
