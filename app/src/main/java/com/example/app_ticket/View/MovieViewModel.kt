package com.example.app_ticket.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Repository.MovieRepository

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    var movies by mutableStateOf(listOf<Movie>())
        private set

    var savedMovies by mutableStateOf(listOf<Movie>())
        private set

    init {
        loadMovies()
        loadSavedMovies()
    }

    fun loadMovies() {
        movies = repository.getMovies()
    }

    fun loadSavedMovies() {
        savedMovies = repository.getSavedMovies()
    }

    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
        loadSavedMovies()
    }

    fun removeMovie(movie: Movie) {
        repository.removeMovie(movie)
        loadSavedMovies()
    }
}
