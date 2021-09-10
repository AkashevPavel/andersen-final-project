package com.example.rickandmortyapp.ui.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.EpisodeRepository
import com.example.rickandmortyapp.data.network.EpisodePagingSource

class EpisodesViewModel: ViewModel() {
    private val repository = EpisodeRepository()

    val episodesFlow = Pager(PagingConfig(pageSize = 20)) {
        EpisodePagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}