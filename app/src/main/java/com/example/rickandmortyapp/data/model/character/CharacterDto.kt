package com.example.rickandmortyapp.data.model.character


import androidx.room.*
import com.example.rickandmortyapp.data.model.location.LocationDto

@Entity(tableName = "characters")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class CharacterDto(
    var episode: List<String> = listOf(),
    var gender: String = "",
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    var id: Int = 0,
    var image: String = "",
    @Embedded(prefix = "location")
    var location: LocationDto = LocationDto(),
    var name: String = "",
    @Embedded(prefix = "origin")
    var origin: LocationDto = LocationDto(),
    var species: String = "",
    var status: String = "",
    var type: String = "",
    var url: String = ""
)