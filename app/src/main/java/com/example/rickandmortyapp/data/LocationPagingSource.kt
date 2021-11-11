package com.example.rickandmortyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.domain.mappers.LocationMapper
import com.example.rickandmortyapp.domain.models.Location
import java.io.IOException
import java.lang.Exception

class LocationPagingSource(
    private val repository: LocationRepository
) : PagingSource<Int, Location>(){
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val pageIndex: Int = params.key ?: 1
            val response = repository.getLocationsPage(pageIndex)
            val locations = checkNotNull(response).results.map { LocationMapper.buildFrom(it) }
            val nextKey = getPage(response.info.next)
            val prevKey = getPage(response.info.prev)

            LoadResult.Page(
                data = locations,
                prevKey = prevKey,
                nextKey = nextKey
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