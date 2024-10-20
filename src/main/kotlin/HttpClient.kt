package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun createHttpClient(): HttpClient{
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
}