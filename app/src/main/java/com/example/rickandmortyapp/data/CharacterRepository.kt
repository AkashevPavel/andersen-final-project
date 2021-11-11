package com.example.rickandmortyapp.data


import com.example.rickandmortyapp.data.db.CharacterDao
import com.example.rickandmortyapp.data.network.ApiClient
import com.example.rickandmortyapp.data.model.character.CharacterDto
import com.example.rickandmortyapp.data.model.character.CharacterResponse
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.domain.mappers.CharacterMapper
import com.example.rickandmortyapp.domain.models.Character
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val characterDao: CharacterDao
) {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = apiClient.getCharacterById(characterId)
        if (!request.isSuccessful) {
            return null
        }

        val relatedEpisodes = getRelatedEpisodes(request.body)
        return CharacterMapper.buildFrom(
            response = request.body,
            relatedEpisodes = relatedEpisodes
        )
    }

    suspend fun getCharactersPage(pageIndex: Int): CharacterResponse? {
        val request = apiClient.getCharactersPage(pageIndex)
        return if (request.isSuccessful) request.body else null
    }

    private suspend fun getRelatedEpisodes(
        characterDto: CharacterDto
    ): List<EpisodeDto> {
        val episodeRange = characterDto.episode.map { episodeUrl ->
            episodeUrl.substring(startIndex = episodeUrl.lastIndexOf('/') + 1)
        }.toString()
        val request = apiClient.getEpisodeRange(episodeRange)
        if (!request.isSuccessful) {
            return emptyList()
        }
        return request.body
    }
}
