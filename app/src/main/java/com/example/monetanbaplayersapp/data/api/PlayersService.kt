package com.example.monetanbaplayersapp.data.api

import com.example.monetanbaplayersapp.data.models.PlayersResponse
import retrofit2.Response

class PlayersService(private val api: PlayersApi) {
    suspend fun getAllPlayers(page: Int): Response<PlayersResponse> {
        return api.getAllPlayers(page)
    }
}
