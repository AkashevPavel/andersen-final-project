package com.example.rickandmortyapp.data.model.episode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeDto(
    val episode: String = "",
    val air_date: String = "",
    @PrimaryKey
    @ColumnInfo(name = "episode_id")
    val id: Int = 0,
    val name: String = "",
    val characters: List<String> = listOf(),
    val url: String = "",
    val created: String = ""
)
