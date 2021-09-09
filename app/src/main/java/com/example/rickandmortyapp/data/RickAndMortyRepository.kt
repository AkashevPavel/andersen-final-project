package com.example.rickandmortyapp.data


import com.example.rickandmortyapp.data.network.NetworkLayer
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto


class RickAndMortyRepository {

    suspend fun getCharacterById(characterId: Int) : CharacterDto? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)
        if (!request.isSuccessful) {
            return null
        }
        return request.body
    }

    suspend fun getCharactersPage(page: Int): CharacterResponseDto? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex = page)
        if (!request.isSuccessful) {
            return null
        }
        return request.body
    }

    suspend fun getLocationById(locationId: Int) : LocationDto? {
        val request = NetworkLayer.apiClient.getLocationById(locationId)
        if (!request.isSuccessful) {
            return null
        }
        return request.body
    }

}
