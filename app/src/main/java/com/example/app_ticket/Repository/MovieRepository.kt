package com.example.app_ticket.Repository

import com.example.app_ticket.Constants.Constants
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Service.MovieResponse
import com.example.app_ticket.Service.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object MovieRepository {

    private val savedMovies = mutableListOf<Movie>()
    private val apiKey = Constants.API_KEY


    suspend fun getMovies(): List<Movie> = try {
        withContext(Dispatchers.IO) {
            val response: MovieResponse = RetrofitInstance.api.getPopularMovies(apiKey)
            response.results
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }


    fun saveMovie(movie: Movie) {
        if (!savedMovies.contains(movie)) {
            savedMovies.add(movie)
        }
    }


    fun removeMovie(movie: Movie) {
        savedMovies.remove(movie)
    }


    fun getSavedMovies(): List<Movie> = savedMovies
}
