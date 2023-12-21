package io.flesh.letscompose.models

class LetsComposePlaces<T : Any>(
    val title: String,
    val author: String = "Aaron",
    val destination: Class<T>
)