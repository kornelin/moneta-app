package com.example.monetanbaplayersapp.data.api
import com.example.monetanbaplayersapp.data.models.PlayersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayersApi {
    @GET("players")
    suspend fun getAllPlayers(@Query("page") page: Int): Response<PlayersResponse>
}
