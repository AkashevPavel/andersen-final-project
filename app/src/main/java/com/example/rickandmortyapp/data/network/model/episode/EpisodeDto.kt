package com.example.rickandmortyapp.data.network.model.episode


data class EpisodeDto(
    val episode: String = "",
    val air_date: String = "",
    val id: Int = 0,
    val name: String = "",
    val characters: List<String> = listOf(),
    val url: String = "",
    val created: String = ""
)
