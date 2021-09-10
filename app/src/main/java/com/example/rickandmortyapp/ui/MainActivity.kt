package com.example.rickandmortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace

import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.character.CharacterInfoFragment
import com.example.rickandmortyapp.ui.character.CharactersFragment
import com.example.rickandmortyapp.ui.episode.EpisodeInfoFragment
import com.example.rickandmortyapp.ui.episode.EpisodesFragment
import com.example.rickandmortyapp.ui.location.LocationInfoFragment
import com.example.rickandmortyapp.ui.location.LocationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main), CharactersFragment.OnCharacterClickedListener,
    LocationInfoFragment.OnRelatedCharacterClickListener, LocationsFragment.OnLocationClickedListener,
    EpisodesFragment.OnEpisodeClickedListener, EpisodeInfoFragment.OnRelatedCharacterClickListener,
    CharacterInfoFragment.OnRelatedLocationClickedListener,
    CharacterInfoFragment.OnRelatedEpisodeClickedListener {

    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigation = findViewById(R.id.bottomNavigation)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.characters_menu -> {
                    supportFragmentManager.commit { replace<CharactersFragment>(R.id.container) }
                    true
                }
                R.id.locations_menu -> {
                    supportFragmentManager.commit { replace<LocationsFragment>(R.id.container) }
                    true
                }
                R.id.episodes_menu -> {
                    supportFragmentManager.commit { replace<EpisodesFragment>(R.id.container) }
                    true
                }
                else -> false
            }
        }
    }

    override fun onCharacterClicked(id: Int) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, CharacterInfoFragment.newInstance(id), CharacterInfoFragment.TAG)
            addToBackStack("character-info")
            commit()
        }
    }

    override fun onLocationClicked(id: Int) {
        supportFragmentManager.beginTransaction().run{
            replace(R.id.container, LocationInfoFragment.newInstance(id), LocationInfoFragment.TAG)
            addToBackStack("location-info")
            commit()
        }
    }

    override fun onEpisodeClicked(id: Int) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, EpisodeInfoFragment.newInstance(id), EpisodeInfoFragment.TAG)
            addToBackStack("episode-info")
            commit()
        }
    }


}