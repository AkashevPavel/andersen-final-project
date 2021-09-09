package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.RickAndMortyRepository
import kotlinx.coroutines.launch

class CharacterInfoViewModel : ViewModel() {

    private val repository = RickAndMortyRepository()

    private val _characterByIdLiveData: MutableLiveData<CharacterDto?> = MutableLiveData()
    val characterByIdLiveData: LiveData<CharacterDto?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}