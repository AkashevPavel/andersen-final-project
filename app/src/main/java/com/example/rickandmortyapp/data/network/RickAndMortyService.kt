package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.character.CharacterResponse
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.model.episode.EpisodeResponse
import com.example.rickandmortyapp.data.model.location.LocationDto
import com.example.rickandmortyapp.data.model.location.LocationResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    ): Response<CharacterResponse>

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
    ): Response<LocationResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") id: Int
    ): Response<EpisodeDto>

    @GET("episode/")
    suspend fun getEpisodesPage(
        @Query("page") pageIndex: Int
    ): Response<EpisodeResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<EpisodeDto>>

    companion object {

        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): RickAndMortyService {
             val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
             val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
             val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
             val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
            return retrofit.create(RickAndMortyService::class.java)
        }
    }
}