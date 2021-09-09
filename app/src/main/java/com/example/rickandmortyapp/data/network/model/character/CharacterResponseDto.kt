package com.example.rickandmortyapp.data.network.model.character

import com.example.rickandmortyapp.data.network.model.PageInfo

data class CharacterResponseDto(
    val info: PageInfo,
    val results: List<CharacterDto> = emptyList()
)