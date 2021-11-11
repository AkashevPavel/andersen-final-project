package com.example.rickandmortyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapp.data.model.CharacterEpisodeCrossRef
import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.model.location.LocationDto


@Database(
    entities = [
        CharacterDto::class,
        EpisodeDto::class,
        LocationDto::class,
        CharacterEpisodeCrossRef::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun episodeDao(): EpisodesDao
    abstract fun characterEpisodeCrossRefDao(): CharacterEpisodeCrossRefDao
    abstract fun characterDao(): CharacterDao

    companion object {
        private const val DATABASE_NAME = "DATABASE_NAME"

        @Volatile
        private var INSTANCE: RickAndMortyDatabase? = null

        fun getInstance(context: Context): RickAndMortyDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RickAndMortyDatabase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}