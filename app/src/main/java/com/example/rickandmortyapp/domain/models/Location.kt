package com.example.rickandmortyapp.domain.models

data class Location(
    val created: String = "",
    val dimension: String = "",
    val id: Int = 0,
    val name: String = "",
    val residents: List<Character> = listOf(),
    val type: String = "",
    val url: String = ""
)
