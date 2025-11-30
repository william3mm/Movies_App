package com.example.app_ticket.Service

import retrofit2.http.Query

interface MovieApiService {

    @Get("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("page") page Int = 1
    ):MovieResponse
}