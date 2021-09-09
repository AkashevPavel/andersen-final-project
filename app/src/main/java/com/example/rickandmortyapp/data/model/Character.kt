package com.example.rickandmortyapp.data.model

import com.example.rickandmortyapp.data.network.model.character.CharacterDto

data class Character(
    val episode: List<Any>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterDto.Location,
    val name: String,
    val origin: CharacterDto.Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)