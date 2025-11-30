package com.example.app_ticket.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.View.MovieViewModel

@Composable
fun SavedMoviesScreen(
    viewModel: MovieViewModel,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }

        LazyColumn {
            items(viewModel.savedMovies) { movie ->
                SavedMovieItem(
                    movie = movie,
                    onRemove = { viewModel.removeMovie(it) }
                )
            }
        }
    }
}

@Composable
fun SavedMovieItem(
    movie: Movie,
    onRemove: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            movie.title,
            modifier = Modifier.weight(1f),
            fontSize = 20.sp
        )

        Button(onClick = { onRemove(movie) }) {
            Text("Remover")
        }
    }
}
