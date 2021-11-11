package com.example.rickandmortyapp.data.model.episode

import com.example.rickandmortyapp.data.model.PageInfo

data class EpisodeResponse(
    val info: PageInfo = PageInfo(),
    val results: List<EpisodeDto> = listOf()
)
