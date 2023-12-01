package com.example.monetanbaplayersapp.data.models

data class PlayersResponse (
    val data: List<Player>,
    val meta: Meta
)

data class Meta (
    val total_pages: Int
)
