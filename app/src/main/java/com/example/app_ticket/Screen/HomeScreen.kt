package com.example.app_ticket.Screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_ticket.Models.Movie

@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    onNavigateToSaved: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = onNavigateToSaved,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Guardados")
        }

        LazyColumn {
            items(viewModel.movies) { movie ->
                MovieItem(
                    movie = movie,
                    onSave = { viewModel.saveMovie(it) }
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onSave: (Movie) -> Unit
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

        Button(onClick = { onSave(movie) }) {
            Text("Guardar")
        }
    }
}
