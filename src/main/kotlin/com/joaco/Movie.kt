package com.joaco

@kotlinx.serialization.Serializable
data class Movie(
    var name: String,
    var studio: String,
    var rating: Float? = 1f
)
