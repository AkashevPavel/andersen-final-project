package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") id: Int
    ) : Response<CharacterDto>

    @GET("character/")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ) : Response<CharacterResponseDto>

    @GET("location/{location-id}")
    suspend fun getLocationById(
        @Path("location-id") id: Int
    ) : Response<LocationDto>
}