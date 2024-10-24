package org.example

import kotlinx.serialization.Serializable

@Serializable

data class Book(
    val title: String,
    val author: String,
    val link: String,
    val votes: Int,
    val rating: Double
)
