package com.example.rickandmortyapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.RickAndMortyRepository
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.toCharacter

import java.io.IOException
import java.lang.Exception

class CharacterPagingSource(
    private val repository: RickAndMortyRepository
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

            val characters = response!!.results.map { it.toCharacter() }

            val prevKey = getPage(response.info.prev)
            val nextKey = getPage(response.info.next)
            LoadResult.Page(characters, prevKey, nextKey)


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