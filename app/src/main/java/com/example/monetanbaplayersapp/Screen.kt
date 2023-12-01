package com.example.monetanbaplayersapp

sealed class Screen(val route: String) {
    object PlayerList : Screen("playerList")
    object PlayerDetail : Screen("playerDetail")
    object TeamDetail : Screen("teamDetail")
}
