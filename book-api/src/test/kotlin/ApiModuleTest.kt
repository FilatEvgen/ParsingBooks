package org.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApplicationTest {
    @Test
    fun testParseEndpoint() {
        testApplication {
            application { module() }
            val response = client.get("/parse")
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("Парсинг завершен и данные сохранены.", response.bodyAsText())
        }
    }

    @Test
    fun testGetBooksByAuthor() {
        testApplication {
            application { module() }
            val response = client.get("/books/author/Викто Пелевин")
            assertEquals(HttpStatusCode.OK, response.status)
            assertNotNull(response.bodyAsText())
        }
    }

    @Test
    fun testGetBooksByRating() {
        testApplication {
            application { module() }
            val response = client.get("/books/rating/4.5")
            assertEquals(HttpStatusCode.OK, response.status)
            assertNotNull(response.bodyAsText())
        }
    }

    @Test
    fun testBadRequestOnInvalidAuthor() {
        testApplication {
            application { module() }
            val response = client.get("/books/author/")
            assertEquals(HttpStatusCode.BadRequest, response.status)
            assertEquals("Параметр автора указан неправильно", response.bodyAsText())
        }
    }

    @Test
    fun testBadRequestOnInvalidRating() {
        testApplication {
            application { module() }
            val response = client.get("/books/rating/invalid")
            assertEquals(HttpStatusCode.BadRequest, response.status)
            assertEquals("Неправильный рейтинг", response.bodyAsText())
        }
    }
}