package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.data.network.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRickAndMortyService(): RickAndMortyService {
        return RickAndMortyService.create()
    }

}