package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeResponseDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import com.example.rickandmortyapp.data.network.model.location.LocationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") id: Int
    ): Response<CharacterDto>

    @GET("character/")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<CharacterResponseDto>

    @GET("character/{character-range}")
    suspend fun getCharacterRange(
        @Path("character-range")characterRange: String
    ): Response<List<CharacterDto>>

    @GET("location/{location-id}")
    suspend fun getLocationById(
        @Path("location-id") id: Int
    ): Response<LocationDto>

    @GET("location/")
    suspend fun getLocationsPage(
        @Query("page") pageIndex: Int
    ): Response<LocationResponseDto>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") id: Int
    ): Response<EpisodeDto>

    @GET("episode/")
    suspend fun getEpisodesPage(
        @Query("page") pageIndex: Int
    ): Response<EpisodeResponseDto>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<EpisodeDto>>
}