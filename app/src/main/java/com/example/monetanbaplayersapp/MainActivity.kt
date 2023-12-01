package com.example.monetanbaplayersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monetanbaplayersapp.ui.theme.MonetaNBAPlayersAppTheme
import com.example.monetanbaplayersapp.viewmodels.PlayerViewModel
import com.example.monetanbaplayersapp.ui.screens.PlayerDetailScreen
import com.example.monetanbaplayersapp.ui.screens.PlayerListScreen
import com.example.monetanbaplayersapp.ui.screens.SplashScreen
import com.example.monetanbaplayersapp.ui.screens.TeamDetailScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val showSplashScreen = MutableStateFlow(true)
    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            showSplashScreen.value = false
        }

        setContent {
            MonetaNBAPlayersAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MonetaNBAPlayersApp(showSplashScreen, viewModel)
                }
            }
        }
    }
}

@Composable
fun MonetaNBAPlayersApp(showSplashScreen: MutableStateFlow<Boolean>, viewModel: PlayerViewModel) {
    val showSplash by showSplashScreen.asStateFlow().collectAsState()
    val networkError by viewModel.networkError.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(networkError) {
        if (networkError) {
            showSplashScreen.value = true
        }
    }

    if (showSplash) {
        if (networkError) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No internet connection. Please check your network and restart the app.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            SplashScreen()
        }
    } else {
        NavHost(navController = navController, startDestination = Screen.PlayerList.route) {
            composable(route = Screen.PlayerList.route) {
                PlayerListScreen(viewModel = viewModel, navController = navController)
            }
            composable(route = Screen.PlayerDetail.route + "/{playerId}") { backStackEntry ->
                val playerId = backStackEntry.arguments?.getString("playerId")?.toIntOrNull()
                val player = viewModel.getPlayerById(playerId)
                PlayerDetailScreen(player, navController)
            }
            composable(route = Screen.TeamDetail.route + "/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                val team = viewModel.getTeamById(teamId)
                TeamDetailScreen(team, navController)
            }
        }
    }
}
