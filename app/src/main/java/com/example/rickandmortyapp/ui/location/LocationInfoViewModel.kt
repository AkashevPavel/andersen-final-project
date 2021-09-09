package com.example.rickandmortyapp.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.RickAndMortyRepository
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Location
import com.example.rickandmortyapp.data.toCharacter
import com.example.rickandmortyapp.data.toLocation
import kotlinx.coroutines.launch

class LocationInfoViewModel: ViewModel() {
    private val repository = RickAndMortyRepository()

    private val _locationByIdLiveData: MutableLiveData<Location> = MutableLiveData()
    val locationByIdLiveData: LiveData<Location> = _locationByIdLiveData

    private val _residentsLiveData: MutableLiveData<Character> = MutableLiveData()
    val residentsLiveData: LiveData<Character> = _residentsLiveData

    fun refreshLocation(locationId: Int) {
        viewModelScope.launch {
            val response = repository.getLocationById(locationId)
            _locationByIdLiveData.postValue(response!!.toLocation())
        }
    }
    fun getResidents(urls: List<String>) {
        for(url in urls) {
            val id = url.split("character/")[1].toInt()
            viewModelScope.launch {
                val response = repository.getCharacterById(id)
                _residentsLiveData.postValue(response!!.toCharacter())
            }
        }
    }
}