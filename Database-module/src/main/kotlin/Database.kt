package org.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun connectToDatabase(config: Config) {
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

fun insertBooks(books: List<Book>) {
    transaction {
        books.forEach { book ->
            BooksTable.insert {
                it[title] = book.title
                it[author] = book.author
                it[link] = book.link
                it[votes] = book.votes
                it[rating] = book.rating
            }
        }
    }
}

// Получение всех книг
fun getAllBooks(): List<Book> {
    return transaction {
        BooksTable.selectAll().map {
            Book(
                title = it[BooksTable.title],
                author = it[BooksTable.author],
                link = it[BooksTable.link],
                votes = it[BooksTable.votes],
                rating = it[BooksTable.rating]
            )
        }
    }
}

// Обновление книги по идентификатору
fun updateBook(id: Int, updatedBook: Book) {
    transaction {
        BooksTable.update({ BooksTable.id eq id }) {
            it[title] = updatedBook.title
            it[author] = updatedBook.author
            it[link] = updatedBook.link
            it[votes] = updatedBook.votes
            it[rating] = updatedBook.rating
        }
    }
}

// Удаление книги по идентификатору
fun deleteBook(id: Int) {
    transaction {
        BooksTable.deleteWhere { BooksTable.id eq id }
    }
}