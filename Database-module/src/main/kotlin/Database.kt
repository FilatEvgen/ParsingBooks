package org.example

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun connectToDatabase(config: Config){
    Database.connect(
        config.database.url,
        driver = config.database.driver,
        user = config.database.user,
        password = config.database.password
    )
    transaction {
        SchemaUtils.create(BooksTable)
    }
}
fun insertBook(book: Book){
    transaction {
        BooksTable.insert {
            it[title] = book.title
            it[author] = book.author
            it[link] =  book.link
            it[votes] = book.votes
            it[rating] = book.rating
        }
    }
}