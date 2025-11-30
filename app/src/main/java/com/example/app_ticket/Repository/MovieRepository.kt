package com.example.app_ticket.Repository

import com.example.app_ticket.Models.Movie

class MovieRepository {

    private val savedMovies = mutableListOf<Movie>()

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                1, "Inception", "https://...", overview = "2.3",
                vote_average = 2.4,
            ),
            Movie(2, "Interstellar", "https://...", overview = "2.3",
                vote_average = 2.4,),
            Movie(3, "Dunkirk", "https://...", overview = "2.3",
                vote_average = 2.4,)
        )
    }

    fun saveMovie(movie: Movie) {
        if (!savedMovies.contains(movie)) {
            savedMovies.add(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        savedMovies.remove(movie)
    }

    fun getSavedMovies(): List<Movie> {
        return savedMovies
    }
}
