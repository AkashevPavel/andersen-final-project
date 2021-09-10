package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.data.network.NetworkLayer
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import com.example.rickandmortyapp.data.network.model.location.LocationResponseDto
import com.example.rickandmortyapp.domain.mappers.LocationMapper
import com.example.rickandmortyapp.domain.models.Location

class LocationRepository {

    suspend fun getLocationById(locationId: Int) : Location? {
        val request = NetworkLayer.apiClient.getLocationById(locationId)
        if (!request.isSuccessful) {
            return null
        }
        val relatedCharacters = getRelatedCharacters(request.body)
        return LocationMapper.buildFrom(
            response = request.body,
            residents = relatedCharacters
        )
    }

    suspend fun getLocationsPage(pageIndex: Int): LocationResponseDto? {
        val request = NetworkLayer.apiClient.getLocationsPage(pageIndex)
        return if (request.isSuccessful) request.body else null
    }

    private suspend fun getRelatedCharacters(locationDto: LocationDto): List<CharacterDto> {
        val relatedCharacters = locationDto.residents.map { characterUrl ->
            characterUrl.substring(characterUrl.lastIndexOf('/') + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getCharacterRange(relatedCharacters)
        return if (!request.isSuccessful) emptyList() else request.body
    }
}