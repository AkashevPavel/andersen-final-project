package com.example.rickandmortyapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.EpisodeRepository
import com.example.rickandmortyapp.domain.models.Episode
import kotlinx.coroutines.launch

class EpisodeInfoViewModel : ViewModel() {

    private val episodeRepository = EpisodeRepository()

    private val _episodeByIdLiveData: MutableLiveData<Episode> = MutableLiveData()
    val episodeByIdLiveData: LiveData<Episode> = _episodeByIdLiveData

    fun refreshEpisode(episodeId: Int) {
        viewModelScope.launch {
            val response = episodeRepository.getEpisodeById(episodeId)
            _episodeByIdLiveData.postValue(response)
        }
    }
}