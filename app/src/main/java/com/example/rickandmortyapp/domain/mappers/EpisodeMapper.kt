package com.example.rickandmortyapp.domain.mappers

import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.domain.models.Episode

object EpisodeMapper {
    fun buildFrom(
        response: EpisodeDto,
        relatedCharacters: List<CharacterDto> = listOf()
    ): Episode {
        return Episode(
            episode = response.episode,
            airDate = response.air_date,
            id = response.id,
            name = response.name,
            characters = relatedCharacters.map {
                CharacterMapper.buildFrom(it)
            }
        )
    }
}