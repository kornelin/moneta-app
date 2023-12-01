package com.example.monetanbaplayersapp.ui.screens

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
import androidx.compose.ui.unit.dp
import com.example.monetanbaplayersapp.data.models.Team
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun TeamDetailScreen(team: Team?, navController: NavController) {
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
                    text = "Team Details",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.h4
                )
            }
        }

        item {
            Text(
                text = "Name: ${team?.name ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Abbreviation: ${team?.abbreviation ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "City: ${team?.city ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Conference: ${team?.conference ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Division: ${team?.division ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Full Name: ${team?.full_name ?: "N/A"}",
                style = MaterialTheme.typography.h6
            )
        }
    }
}
