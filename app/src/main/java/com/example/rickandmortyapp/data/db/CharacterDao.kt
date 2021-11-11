package com.example.rickandmortyapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.character.CharacterResponse
import com.example.rickandmortyapp.data.model.character.CharacterWithEpisodes
import com.example.rickandmortyapp.domain.models.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao: BaseDao<CharacterDto> {

    @Query("SELECT * FROM characters WHERE character_id = :charId")
    suspend fun getCharacterByIdWithEpisodes(charId: Int): CharacterWithEpisodes

    @Query("SELECT * FROM characters LIMIT :size")
    suspend fun getCharactersPage(size: Int): List<CharacterDto>

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): List<CharacterDto>?
}