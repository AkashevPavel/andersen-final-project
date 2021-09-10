package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.network.CharacterPagingSource

class CharactersViewModel : ViewModel() {

    private val repository = CharacterRepository()

    val charactersFlow = Pager(PagingConfig(pageSize = 20))
    { CharacterPagingSource(repository) }.flow.cachedIn(viewModelScope)

}