package com.example.rickandmortyapp.data.network.model

data class PageInfo(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)