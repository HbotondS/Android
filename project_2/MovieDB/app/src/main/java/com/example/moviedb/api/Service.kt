package com.example.moviedb.api

import com.example.moviedb.models.MovieResponse
import com.example.moviedb.models.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideo(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<VideoResponse>
}