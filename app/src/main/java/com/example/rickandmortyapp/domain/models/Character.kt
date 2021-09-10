package com.example.rickandmortyapp.domain.models

data class Character(
    val episode: List<Episode> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Location = Location(),
    val species: String = "",
    val status: String = "",
    val type: String = "",
)
