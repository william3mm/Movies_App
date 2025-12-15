package com.example.app_ticket.Repository

import com.example.app_ticket.Constants.Constants
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Service.MovieResponse
import com.example.app_ticket.Service.RetrofitInstance
import com.example.app_ticket.Interface.MovieDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepository(
    private val movieDao: MovieDao
) {

    private val apiKey = Constants.API_KEY
    private val firestore = FirebaseFirestore.getInstance()
    private val collectionName = "saved_movies"

    fun getSavedMovies(): Flow<List<Movie>> =
        movieDao.getAllMovies()

    suspend fun fetchMoviesFromApi(): List<Movie> = try {
        withContext(Dispatchers.IO) {
            val response: MovieResponse =
                RetrofitInstance.api.getPopularMovies(apiKey)

            movieDao.insertAll(response.results)
            response.results
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }

    suspend fun saveMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.insert(movie)

            firestore.collection(collectionName)
                .document(movie.id.toString())
                .set(movie)
        }
    }

    suspend fun removeMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.delete(movie)

            firestore.collection(collectionName)
                .document(movie.id.toString())
                .delete()
        }
    }
}
