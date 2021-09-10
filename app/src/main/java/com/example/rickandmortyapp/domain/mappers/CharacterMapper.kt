package com.example.rickandmortyapp.domain.mappers

import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeDto
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.models.Location

object CharacterMapper {
    fun buildFrom(
        response: CharacterDto,
        relatedEpisodes: List<EpisodeDto> = listOf()
    ): Character {
        return Character(
            name = response.name,
            id = response.id,
            episode = relatedEpisodes.map {
                EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            image = response.image,
            location = Location(
                name = response.location.name,
                url = response.location.url
            ),
            origin = Location(
                name = response.origin.name,
                url = response.origin.url
            ),
            status = response.status,
            type = response.status,
            species = response.species

        )
    }
}