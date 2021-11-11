package com.example.rickandmortyapp.ui.viewmodels

import androidx.lifecycle.*
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.domain.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor (
    private val repository: CharacterRepository,
) : ViewModel() {

    private val _characterByIdLiveData: MutableLiveData<Character?> = MutableLiveData()
    val characterByIdLiveData: LiveData<Character?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}