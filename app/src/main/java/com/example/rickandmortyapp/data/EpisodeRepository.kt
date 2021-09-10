package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.data.network.NetworkLayer
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeResponseDto
import com.example.rickandmortyapp.domain.mappers.EpisodeMapper
import com.example.rickandmortyapp.domain.models.Episode

class EpisodeRepository {

    suspend fun getEpisodesPage(pageIndex: Int): EpisodeResponseDto? {
        val request = NetworkLayer.apiClient.getEpisodesPage(pageIndex)
        return if (request.isSuccessful) request.body else null
    }

    suspend fun getEpisodeById(episodeId: Int): Episode? {
        val request = NetworkLayer.apiClient.getEpisodeById(episodeId)
        if (!request.isSuccessful) {
            return null
        }
        val relatedCharacters = getRelatedCharacters(request.body)
        return EpisodeMapper.buildFrom(
            response = request.body,
            relatedCharacters = relatedCharacters
        )
    }

    private suspend fun getRelatedCharacters(episodeDto: EpisodeDto): List<CharacterDto> {
        val relatedCharacters = episodeDto.characters.map { characterUrl ->
            characterUrl.substring(characterUrl.lastIndexOf('/') + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getCharacterRange(relatedCharacters)
        return if (!request.isSuccessful) emptyList() else request.body
    }
}