package com.example.rickandmortyapp.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.domain.models.Character
import kotlinx.coroutines.launch

class CharacterInfoViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _characterByIdLiveData: MutableLiveData<Character?> = MutableLiveData()
    val characterByIdLiveData: LiveData<Character?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}