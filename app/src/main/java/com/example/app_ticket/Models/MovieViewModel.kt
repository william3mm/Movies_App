package com.example.app_ticket.View

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    // Filmes vindos do Room
    val savedMovies: StateFlow<List<Movie>> =
        repository.getSavedMovies()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    // Filmes carregados da API
    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    init {
        refreshMovies()
    }

    fun refreshMovies() {
        viewModelScope.launch {
            movies = repository.fetchMoviesFromApi()
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            repository.saveMovie(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        viewModelScope.launch {
            repository.removeMovie(movie)
        }
    }
}
