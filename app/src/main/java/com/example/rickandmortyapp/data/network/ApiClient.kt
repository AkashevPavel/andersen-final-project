package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.character.CharacterResponse
import com.example.rickandmortyapp.data.model.SimpleResponse
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.model.episode.EpisodeResponse
import com.example.rickandmortyapp.data.model.location.LocationDto
import com.example.rickandmortyapp.data.model.location.LocationResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ApiClient @Inject constructor(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<CharacterDto> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }
    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<CharacterResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }
    suspend fun getCharacterRange(characterRange: String): SimpleResponse<List<CharacterDto>> {
        return safeApiCall { rickAndMortyService.getCharacterRange(characterRange) }
    }
    suspend fun getLocationById(locationId: Int): SimpleResponse<LocationDto> {
        return safeApiCall { rickAndMortyService.getLocationById(locationId) }
    }
    suspend fun getLocationsPage(pageIndex: Int): SimpleResponse<LocationResponse> {
        return safeApiCall { rickAndMortyService.getLocationsPage(pageIndex) }
    }
    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<EpisodeDto> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }
    suspend fun getEpisodesPage(pageIndex: Int): SimpleResponse<EpisodeResponse> {
        return safeApiCall { rickAndMortyService.getEpisodesPage(pageIndex) }
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