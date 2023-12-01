package com.example.monetanbaplayersapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.monetanbaplayersapp.Screen
import com.example.monetanbaplayersapp.data.models.Player
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment

@Composable
fun PlayerDetailScreen(player: Player?, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    text = "Player Details",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.h4
                )
            }
        }
        item {
            Text(
                text = "Name: ${player?.first_name ?: "N/A"} ${player?.last_name ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Position: ${if (player!!.position.isNullOrEmpty()) "N/A" else player!!.position}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Height: ${player?.height_feet?.toString() ?: "N/A"} feet ${player?.height_inches?.toString() ?: "N/A"} inches",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Weight: ${player?.weight_pounds?.toString() ?: "N/A"} pounds",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Team: ${player?.team?.name ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Click here for team details",
                modifier = Modifier.clickable {
                    player?.let {
                        navController.navigate(Screen.TeamDetail.route + "/${it.team.id}")
                    }
                },
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}
