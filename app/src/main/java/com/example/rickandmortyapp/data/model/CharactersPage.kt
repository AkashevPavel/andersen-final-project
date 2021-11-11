package com.example.rickandmortyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapp.data.model.character.CharacterDto

@Entity(tableName = "characters_page")
data class CharactersPage(
    val next: String? = null,
    val prev: String? = null,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "current_page")
    val currentPage: Int = if (prev == null) 1 else prev.split("?page=")[1].toInt() + 1,
    val characters: List<CharacterDto>
)
