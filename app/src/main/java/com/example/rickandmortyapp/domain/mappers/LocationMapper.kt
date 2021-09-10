package com.example.rickandmortyapp.domain.mappers

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import com.example.rickandmortyapp.domain.models.Location

object LocationMapper {
    fun buildFrom(
        response: LocationDto,
        residents: List<CharacterDto> = listOf()
    ): Location {
        return Location(
            id = response.id,
            name = response.name,
            residents = residents.map {
                CharacterMapper.buildFrom(it)
            },
            dimension = response.dimension,
            type = response.type
        )
    }
}