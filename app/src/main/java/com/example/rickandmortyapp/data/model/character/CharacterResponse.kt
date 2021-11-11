package com.example.rickandmortyapp.data.model.character

import com.example.rickandmortyapp.data.model.PageInfo

data class CharacterResponse(
    val info: PageInfo,
    val results: List<CharacterDto> = listOf()
)
