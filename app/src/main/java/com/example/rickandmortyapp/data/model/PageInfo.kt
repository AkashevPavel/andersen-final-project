package com.example.rickandmortyapp.data.model

data class PageInfo(
    val next: String? = null,
    val prev: String? = null
) {
    val nextKey: Int? = next?.split("?page=")?.get(1)?.toInt()
    val prevKey: Int? = prev?.split("?page=")?.get(1)?.toInt()
}

