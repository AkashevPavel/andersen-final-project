package com.example.rickandmortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace

import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.character.CharacterInfoFragment
import com.example.rickandmortyapp.ui.character.CharactersFragment
import com.example.rickandmortyapp.ui.location.LocationInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main), CharactersFragment.OnClickListener,
    LocationInfoFragment.OnResidentClickListener {

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
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.container, LocationInfoFragment.newInstance(4), CharacterInfoFragment.TAG)
                        addToBackStack("location info")
                        commit()
                    }
                    true
                }
                R.id.episodes_menu -> {
                    supportFragmentManager.commit { replace<CharactersFragment>(R.id.container) }
                    true
                }
                else -> false
            }
        }
    }

    override fun onClick(id: Int) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, CharacterInfoFragment.newInstance(id), CharacterInfoFragment.TAG)
            addToBackStack(CharacterInfoFragment.TAG)
            commit()
        }
    }


}