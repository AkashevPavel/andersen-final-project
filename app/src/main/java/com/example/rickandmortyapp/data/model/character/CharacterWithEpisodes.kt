package com.example.rickandmortyapp.data.model.character

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rickandmortyapp.data.model.CharacterEpisodeCrossRef
import com.example.rickandmortyapp.data.model.episode.EpisodeDto

data class CharacterWithEpisodes(
    @Embedded val character: CharacterDto,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "episode_id",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    var episodes: List<EpisodeDto>
)
