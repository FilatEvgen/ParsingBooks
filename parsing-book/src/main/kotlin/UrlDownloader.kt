package org.example

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class UrlDownloader(private val client: HttpClient) {
    suspend fun download(url: String): String {
        val response: HttpResponse = client.get(url)
        return response.bodyAsText()
    }
}
