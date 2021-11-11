package com.example.rickandmortyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.domain.mappers.EpisodeMapper
import com.example.rickandmortyapp.domain.models.Episode
import java.io.IOException
import java.lang.Exception

class EpisodePagingSource(
    private val episodeRepository: EpisodeRepository
) : PagingSource<Int, Episode>() {
    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val pageIndex = params.key ?: 1
            val response = episodeRepository.getEpisodesPage(pageIndex)
            val episodes = checkNotNull(response).results.map { EpisodeMapper.buildFrom(it) }
            val prevKey = getPage(response.info.prev)
            val nextKey = getPage(response.info.next)
            LoadResult.Page(
                data = episodes,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getPage(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}