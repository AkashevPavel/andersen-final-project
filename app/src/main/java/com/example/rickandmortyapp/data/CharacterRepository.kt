package com.example.rickandmortyapp.data


import com.example.rickandmortyapp.data.network.NetworkLayer
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.character.CharacterResponseDto
import com.example.rickandmortyapp.data.network.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto
import com.example.rickandmortyapp.domain.mappers.CharacterMapper
import com.example.rickandmortyapp.domain.models.Character


class CharacterRepository {

    companion object {
        private const val ORIGIN = "ORIGIN"
        private const val CURRENT = "CURRENT"
    }

    suspend fun getCharacterById(characterId: Int) : Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)
        if (!request.isSuccessful) {
            return null
        }
        val relatedEpisodes = getRelatedEpisodes(request.body)
        return CharacterMapper.buildFrom(
            response = request.body,
            relatedEpisodes = relatedEpisodes
        )
    }

    suspend fun getCharactersPage(page: Int): CharacterResponseDto? {
        val request = NetworkLayer.apiClient.getCharactersPage(page)
        return if (request.isSuccessful) request.body else null
    }


    private suspend fun getRelatedEpisodes(
        characterDto: CharacterDto
    ): List<EpisodeDto> {
        val episodeRange = characterDto.episode.map { episodeUrl ->
            episodeUrl.substring(startIndex = episodeUrl.lastIndexOf('/') + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)
        if (!request.isSuccessful) {
            return emptyList()
        }
        return request.body
    }
}
