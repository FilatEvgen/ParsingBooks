package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class BookParserTests {

    @Test
    fun `test parseBooks with valid HTML`() {
        val html = """
            <div class="ArtInfo_wrapper__GoMsb">
                <a data-testid="art__title" href="http://example.com/book1">
                    <p class="ArtInfo_title__h_5Ay">Book Title 1</p>
                </a>
                <a data-testid="art__authorName">Author 1</a>
                <div class="ArtRating_rating__ntve8" data-testid="art__ratingAvg">4.5</div>
                <div class="ArtRating_votes__MIJS1" data-testid="art__ratingCount">100</div>
            </div>
            <div class="ArtInfo_wrapper__GoMsb">
                <a data-testid="art__title" href="http://example.com/book2">
                    <p class="ArtInfo_title__h_5Ay">Book Title 2</p>
                </a>
                <a data-testid="art__authorName">Author 2</a>
                <div class="ArtRating_rating__ntve8" data-testid="art__ratingAvg">3.8</div>
                <div class="ArtRating_votes__MIJS1" data-testid="art__ratingCount">50</div>
            </div>
        """.trimIndent()

        val expectedBooks = listOf(
            Book("Book Title 1", "Author 1", "http://example.com/book1", 100, 4.5),
            Book("Book Title 2", "Author 2", "http://example.com/book2", 50, 3.8)
        )

        val parsedBooks = parseBooks(html)

        assertEquals(expectedBooks.size, parsedBooks.size)
        expectedBooks.forEachIndexed { index, book ->
            assertEquals(book.title, parsedBooks[index].title)
            assertEquals(book.author, parsedBooks[index].author)
            assertEquals(book.link, parsedBooks[index].link)
            assertEquals(book.votes, parsedBooks[index].votes)
            assertEquals(book.rating, parsedBooks[index].rating)
        }
    }

    @Test
    fun `test parseBooks with empty HTML`() {
        val html = ""

        val parsedBooks = parseBooks(html)

        assertEquals(0, parsedBooks.size)
    }

    @Test
    fun `test parseBooks with malformed HTML`() {
        val html = """
            <div class="ArtInfo_wrapper__GoMsb">
                <a data-testid="art__title" href="http://example.com/book1">
                    <p class="ArtInfo_title__h_5Ay">Book Title 1</p>
                </a>
                <a data-testid="art__authorName">Author 1</a>
                <div class="ArtRating_rating__ntve8" data-testid="art__ratingAvg">4.5</div>
                <div class="ArtRating_votes__MIJS1" data-testid="art__ratingCount">InvalidVotes</div>
            </div>
        """.trimIndent()

        val parsedBooks = parseBooks(html)

        assertEquals(0, parsedBooks.size) // Ожидаем, что книга не будет добавлена из-за некорректных данных
    }
}