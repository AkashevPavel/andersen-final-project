package com.example.rickandmortyapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.CharacterPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    val charactersFlow = Pager(PagingConfig(pageSize = 20))
    { CharacterPagingSource(repository) }.flow.cachedIn(viewModelScope)
}
