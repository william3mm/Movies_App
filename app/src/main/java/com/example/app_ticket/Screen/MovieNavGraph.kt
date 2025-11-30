package com.example.app_ticket.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_ticket.View.MovieViewModel

@Composable
fun MovieNavGraph() {
    val navController = rememberNavController()

    val viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToSaved = { navController.navigate("saved") }
            )
        }
        composable("saved") {
            SavedMoviesScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
