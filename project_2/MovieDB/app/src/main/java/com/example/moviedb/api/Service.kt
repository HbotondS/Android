package com.example.moviedb.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.moviedb.models.MovieResponse

interface Service {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>
}