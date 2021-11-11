package com.example.rickandmortyapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.LocationRepository
import com.example.rickandmortyapp.domain.models.Location
import kotlinx.coroutines.launch

class LocationInfoViewModel: ViewModel() {
    private val repository = LocationRepository()

    private val _locationByIdLiveData: MutableLiveData<Location> = MutableLiveData()
    val locationByIdLiveData: LiveData<Location> = _locationByIdLiveData

    fun refreshLocation(locationId: Int) {
        viewModelScope.launch {
            val response = repository.getLocationById(locationId)
            _locationByIdLiveData.postValue(response)
        }
    }

}