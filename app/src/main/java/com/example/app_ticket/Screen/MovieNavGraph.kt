package com.example.app_ticket.Screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_ticket.View.MovieViewModel

@Composable
fun MovieNavGraph() {
    val navController = rememberNavController()
    val viewModel: MovieViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToSaved = {
                    viewModel.loadSavedMovies()
                    println("Filmes guardados antes de navegar: ${viewModel.savedMovies.size}")
                    navController.navigate("saved")
                }
            )
        }

        composable("saved") {
            LaunchedEffect(viewModel.savedMovies) {
                println("Entrando em SavedMoviesScreen com ${viewModel.savedMovies.size} filmes")
            }

            SavedMoviesScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }

}
