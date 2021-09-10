package com.example.rickandmortyapp.data.network.model.character


import com.example.rickandmortyapp.data.network.model.location.LocationDto

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto,
    val name: String,
    val origin: LocationDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)