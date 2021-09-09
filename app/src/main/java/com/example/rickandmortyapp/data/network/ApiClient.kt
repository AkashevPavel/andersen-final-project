package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.SimpleResponse
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import retrofit2.Response
import java.lang.Exception

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<CharacterDto> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }
    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<CharacterResponseDto> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }
    suspend fun getLocationById(locationId: Int): SimpleResponse<LocationDto> {
        return safeApiCall { rickAndMortyService.getLocationById(locationId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}