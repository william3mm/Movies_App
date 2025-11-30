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
fun HomeScreen(
    viewModel: MovieViewModel,
    onNavigateToSaved: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        Button(
            onClick = onNavigateToSaved,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Ver Filmes Guardados")
        }

        MovieGrid(viewModel = viewModel)
    }

}

@Composable
fun MovieGrid(viewModel: MovieViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(viewModel.movies) { movie ->
            MovieCard(
                movie = movie,
                onSave = { viewModel.saveMovie(it) }
            )
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onSave: (Movie) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "[https://image.tmdb.org/t/p/w500${movie.poster_path}](https://image.tmdb.org/t/p/w500${movie.poster_path})",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "⭐ ${movie.vote_average}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = movie.overview,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de salvar
            Button(
                onClick = { onSave(movie) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }

}
