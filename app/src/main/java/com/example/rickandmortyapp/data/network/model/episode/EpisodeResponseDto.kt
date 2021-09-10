package com.example.rickandmortyapp.data.network.model.episode

import com.example.rickandmortyapp.data.network.model.PageInfo

data class EpisodeResponseDto(
    val info: PageInfo = PageInfo(),
    val results: List<EpisodeDto> = listOf()
)
