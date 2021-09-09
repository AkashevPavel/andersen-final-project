package com.example.rickandmortyapp.data.network.model.location

import com.example.rickandmortyapp.data.network.model.PageInfo

data class LocationResponseDto(
    val info: PageInfo,
    val results: List<LocationDto> = emptyList()
)
