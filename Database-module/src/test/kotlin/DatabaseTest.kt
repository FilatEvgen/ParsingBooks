package org.example

import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseTest {

    @BeforeTest
    fun setup() {
        connectToTestDatabase()
    }

    @AfterTest
    fun tearDown() {
        transaction {
            drop(BooksTable)
        }
    }

    @Test
    fun testInsertBooks() {
        val books = listOf(
            Book("Книга 1", "Автор 1", "link1", 10, 4.5),
            Book("Книга 2", "Автор 2", "link2", 20, 4.0)
        )

        insertBooks(books)

        val retrievedBooks = getAllBooks()
        assertEquals(2, retrievedBooks.size)
        assertEquals("Книга 1", retrievedBooks[0].title)
        assertEquals("Автор 1", retrievedBooks[0].author)
    }

    @Test
    fun testGetAllBooks() {
        insertBooks(listOf(
            Book("Книга 1", "Автор 1", "link1", 10, 4.5),
            Book("Книга 2", "Автор 2", "link2", 20, 4.0)
        ))

        val books = getAllBooks()
        assertEquals(2, books.size)
    }

    @Test
    fun testUpdateBook() {
        insertBooks(listOf(Book("Книга 1", "Автор 1", "link1", 10, 4.5)))

        val updatedBook = Book("Книга 1 (Обновленная)", "Автор 1", "link1", 15, 4.8)
        updateBook(1, updatedBook)

        val retrievedBooks = getAllBooks()
        assertEquals(1, retrievedBooks.size)
        assertEquals("Книга 1 (Обновленная)", retrievedBooks[0].title)
        assertEquals(15, retrievedBooks[0].votes)
    }

    @Test
    fun testDeleteBook() {
        insertBooks(listOf(Book("Книга 1", "Автор 1", "link1", 10, 4.5)))

        deleteBook(1)

        val books = getAllBooks()
        assertEquals(0, books.size)
    }
}