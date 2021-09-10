package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.SimpleResponse
import com.example.rickandmortyapp.data.network.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import com.example.rickandmortyapp.data.network.model.location.LocationResponseDto
import retrofit2.Response
import java.lang.Exception

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<CharacterDto> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }
    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<CharacterResponseDto> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }
    suspend fun getCharacterRange(characterRange: String): SimpleResponse<List<CharacterDto>> {
        return safeApiCall { rickAndMortyService.getCharacterRange(characterRange) }
    }
    suspend fun getLocationById(locationId: Int): SimpleResponse<LocationDto> {
        return safeApiCall { rickAndMortyService.getLocationById(locationId) }
    }
    suspend fun getLocationsPage(pageIndex: Int): SimpleResponse<LocationResponseDto> {
        return safeApiCall { rickAndMortyService.getLocationsPage(pageIndex) }
    }
    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<EpisodeDto> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }
    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<EpisodeDto>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}