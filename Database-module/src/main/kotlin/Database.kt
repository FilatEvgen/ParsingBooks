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
fun insertBook(title: String, author: String, link: String, votes: Int, rating: Double){
    transaction {
        BooksTable.insert {
            it[BooksTable.title] = title
            it[BooksTable.author] = author
            it[BooksTable.link] =  link
            it[BooksTable.votes] = votes
            it[BooksTable.rating] = rating
        }
    }
}