package com.example.app_ticket.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.View.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    onNavigateToSaved: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp)
        ) {

            Button(
                onClick = {
                    viewModel.loadSavedMovies()
                    println("Filmes guardados: ${viewModel.savedMovies.size}")
                    onNavigateToSaved()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Filmes Guardados")
            }

            MovieGrid(
                viewModel = viewModel,
                onShowMessage = { message ->
                    scope.launch { snackbarHostState.showSnackbar(message) }
                }
            )
        }
    }

}

@Composable
fun MovieGrid(
    viewModel: MovieViewModel,
    onShowMessage: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(viewModel.movies) { movie ->
            MovieCard(
                movie = movie,
                onSave = { viewModel.saveMovie(it) },
                onShowMessage = onShowMessage
            )
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onSave: (Movie) -> Unit,
    onShowMessage: (String) -> Unit
) {
    val posterUrl = movie.poster_path?.let { "[https://image.tmdb.org/t/p/w500$it](https://image.tmdb.org/t/p/w500$it)" }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            posterUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = movie.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "⭐ ${movie.vote_average}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Yellow
                        )
                    }

                    Column {
                        Text(
                            text = movie.overview,
                            fontSize = 12.sp,
                            color = Color.White,
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                try {
                                    onSave(movie)
                                    onShowMessage("Filme guardado com sucesso!")
                                } catch (e: Exception) {
                                    onShowMessage("Erro ao guardar o filme: ${e.message}")
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Guardar")
                        }
                    }
                }
            }
        }
    }

}
