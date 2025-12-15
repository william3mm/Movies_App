package com.example.app_ticket.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SavedMoviesScreen(
    viewModel: MovieViewModel,
    onBack: () -> Unit
) {
    println("Renderizando SavedMoviesScreen com ${viewModel.savedMovies.size} filmes") // Debug

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Voltar")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.savedMovies) { movie ->
                SavedMovieCard(
                    movie = movie,
                    onRemove = { viewModel.removeMovie(it) }
                )
            }
        }
    }

}

@Composable
fun SavedMovieCard(
    movie: Movie,
    onRemove: (Movie) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            movie.poster_path?.let { poster ->
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$poster",
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

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
                            onClick = { onRemove(movie) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Remover")
                        }
                    }
                }
            }
        }
    }

}