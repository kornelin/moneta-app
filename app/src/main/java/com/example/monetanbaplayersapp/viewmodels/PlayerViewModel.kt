package com.example.monetanbaplayersapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monetanbaplayersapp.data.api.PlayersService
import com.example.monetanbaplayersapp.data.models.Player
import com.example.monetanbaplayersapp.data.models.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val playersService: PlayersService): ViewModel() {
    private var currentPage = 1
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players
    private var totalPages = Int.MAX_VALUE
    private val _networkError = MutableStateFlow(false)
    val networkError: StateFlow<Boolean> = _networkError

    init {
        fetchAllPlayers()
    }

    fun fetchAllPlayers() {
        viewModelScope.launch {
            if (currentPage <= totalPages) {
                try {
                    val response = playersService.getAllPlayers(currentPage)
                    if (response.isSuccessful && response.body() != null) {
                        _players.value += response.body()!!.data
                        totalPages = response.body()!!.meta.total_pages
                        currentPage++
                    } else {
                        _networkError.value = true
                        val errorCode = response.code()
                        val errorMessage = response.errorBody()?.string() ?: "unknown error"
                        Log.e("PLAYERS_API", "Non-successful response received. Error code: $errorCode, Message: $errorMessage")
                    }
                } catch (e: Exception) {
                    _networkError.value = true
                    Log.e("PLAYERS_API", "FAILED to send a request: ${e.message}")
                }
            }
        }
    }

    fun getPlayerById(id: Int?): Player? {
        return players.value.find { player -> player.id == id }
    }

    fun getTeamById(id: Int?): Team? {
        return players.value.map { it.team }.find { team -> team.id == id }
    }
}
