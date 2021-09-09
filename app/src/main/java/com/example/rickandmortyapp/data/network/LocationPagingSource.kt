package com.example.rickandmortyapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.RickAndMortyRepository
import com.example.rickandmortyapp.data.model.Location
import com.example.rickandmortyapp.data.toCharacter
import java.io.IOException
import java.lang.Exception

class LocationPagingSource(
    private val repository: RickAndMortyRepository
) : PagingSource<Int, Location>(){
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val pageIndex: Int = params.key ?: 1

            TODO()


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