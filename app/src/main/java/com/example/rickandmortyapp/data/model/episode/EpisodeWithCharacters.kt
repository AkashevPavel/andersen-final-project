package com.example.rickandmortyapp.data.model.episode

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rickandmortyapp.data.model.CharacterEpisodeCrossRef
import com.example.rickandmortyapp.data.model.character.CharacterDto

data class EpisodeWithCharacters(
    @Embedded
    val episode: EpisodeDto,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "character_id",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    var characters: List<CharacterDto> = listOf()
)
