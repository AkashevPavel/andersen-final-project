package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Location
import com.example.rickandmortyapp.data.network.model.character.CharacterDto
import com.example.rickandmortyapp.data.network.model.location.LocationDto

internal fun CharacterDto.toCharacter() = Character(episode, gender, id, image, location, name, origin, species, status, type, url)


internal fun LocationDto.toLocation() = Location(created, dimension, id, name, residents, type, url)
