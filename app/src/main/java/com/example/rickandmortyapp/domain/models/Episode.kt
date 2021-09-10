package com.example.rickandmortyapp.domain.models

data class Episode(
    val episode: String = "",
    val airDate: String = "",
    val id: Int = 0,
    val name: String = "",
    val characters: List<Character> = listOf()
)
