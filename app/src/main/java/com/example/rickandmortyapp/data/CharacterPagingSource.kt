package com.example.rickandmortyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.mappers.CharacterMapper

import java.io.IOException
import java.lang.Exception

class CharacterPagingSource(
    private val repository: CharacterRepository
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val pageIndex: Int = params.key ?: 1

            val response = repository.getCharactersPage(pageIndex)
            val characters = checkNotNull(response).results.map { CharacterMapper.buildFrom(it) }

            val prevKey = response.info.prevKey
            val nextKey = response.info.nextKey

            LoadResult.Page(characters, prevKey, nextKey)


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}