package com.example.rickandmortyapp.data.model.location

import com.example.rickandmortyapp.data.model.PageInfo

data class LocationResponse(
    val info: PageInfo,
    val results: List<LocationDto> = listOf()
)
