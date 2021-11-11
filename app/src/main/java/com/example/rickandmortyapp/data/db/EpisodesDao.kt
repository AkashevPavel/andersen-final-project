package com.example.rickandmortyapp.data.db


import androidx.room.Dao
import androidx.room.Query
import com.example.rickandmortyapp.data.model.episode.EpisodeDto
import com.example.rickandmortyapp.data.model.episode.EpisodeWithCharacters

@Dao
interface EpisodesDao: BaseDao<EpisodeDto> {
    @Query("SELECT * FROM episodes WHERE episode_id = :episodeId")
    suspend fun getEpisodeByIdWithCharacters(episodeId: Int): EpisodeWithCharacters
}
