package com.example.monetanbaplayersapp.data.models

data class Player (
    val id: Int?,
    val first_name: String?,
    val last_name: String?,
    val position: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val weight_pounds: Int?,
    val team: Team
)
