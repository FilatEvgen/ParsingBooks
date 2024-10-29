package org.example

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun htmlContent(client: HttpClient, url: String): String {
    val response: HttpResponse = client.get(url)
    return response.bodyAsText()
}