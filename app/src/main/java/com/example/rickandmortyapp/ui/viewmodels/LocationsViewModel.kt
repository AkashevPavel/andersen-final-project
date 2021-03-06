package com.example.rickandmortyapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.LocationRepository
import com.example.rickandmortyapp.data.LocationPagingSource

class LocationsViewModel: ViewModel() {

    private val repository = LocationRepository()

    val locationsFlow = Pager(PagingConfig(pageSize = 20)) {
        LocationPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}