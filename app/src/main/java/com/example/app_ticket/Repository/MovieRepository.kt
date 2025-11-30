package com.example.app_ticket.Repository

import com.example.app_ticket.Constants.Constants
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Service.MovieResponse
import com.example.app_ticket.Service.RetrofitInstance
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieRepository {

    private val savedMovies = mutableListOf<Movie>()
    private val apiKey = Constants.API_KEY
    private val firestore = FirebaseFirestore.getInstance()
    private val collectionName = "saved_movies"

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

            // Salvar no Firebase
            firestore.collection(collectionName)
                .document(movie.id.toString())
                .set(movie)
                .addOnSuccessListener {
                    println("Filme salvo no Firebase: ${movie.title}")
                }
                .addOnFailureListener { e ->
                    println("Erro ao salvar no Firebase: ${e.message}")
                }
        }
    }

    fun removeMovie(movie: Movie) {
        savedMovies.remove(movie)
    }

    fun getSavedMovies(): List<Movie> = savedMovies

}
