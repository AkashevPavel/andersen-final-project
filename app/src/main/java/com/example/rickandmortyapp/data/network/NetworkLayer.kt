package com.example.rickandmortyapp.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val rickAndMortyService: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }

    val apiClient = ApiClient(rickAndMortyService)


}