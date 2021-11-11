package com.example.rickandmortyapp.data.db

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

class Converters {

    @TypeConverter
    fun stringToList(listAsString: String): List<String> {
        return listAsString.split(",").map { it.trim() }
    }
    @TypeConverter
    fun listToString(listOfStrings: List<String>): String {
        return listOfStrings.joinToString(separator = ",")
    }
}