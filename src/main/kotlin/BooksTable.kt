package org.example

import org.jetbrains.exposed.dao.id.IntIdTable

object BooksTable : IntIdTable() {
    val title = varchar("title", 255)
    val author = varchar("author", 255)
    val link = varchar("link", 255)
    val votes = integer("votes")
    val rating = double("rating")

}