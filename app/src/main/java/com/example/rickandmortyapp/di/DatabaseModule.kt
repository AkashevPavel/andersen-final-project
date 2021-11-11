package com.example.rickandmortyapp.di

import android.content.Context
import com.example.rickandmortyapp.data.db.CharacterDao
import com.example.rickandmortyapp.data.db.CharacterEpisodeCrossRefDao
import com.example.rickandmortyapp.data.db.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRickAndMortyDatabase(@ApplicationContext context: Context): RickAndMortyDatabase {
        return RickAndMortyDatabase.getInstance(context)
    }

    @Provides
    fun provideCharacterDao(rickAndMortyDatabase: RickAndMortyDatabase): CharacterDao {
        return rickAndMortyDatabase.characterDao()
    }
    @Provides
    fun provideCharacterEpisodeCrossRefDao(rickAndMortyDatabase: RickAndMortyDatabase): CharacterEpisodeCrossRefDao {
        return rickAndMortyDatabase.characterEpisodeCrossRefDao()
    }
}