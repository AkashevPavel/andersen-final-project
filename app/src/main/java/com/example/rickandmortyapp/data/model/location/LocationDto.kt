package com.example.rickandmortyapp.data.model.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationDto(
    val created: String = "",
    val dimension: String = "",
    @PrimaryKey
    @ColumnInfo(name = "location_id")
    val id: Int = 0,
    val name: String = "",
    val residents: List<String> = listOf(),
    val type: String = "",
    val url: String = ""
)