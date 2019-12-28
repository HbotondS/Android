package com.example.moviedb.utils

import com.example.moviedb.BuildConfig
import com.google.firebase.database.FirebaseDatabase

class Constants {
    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        val API_KEY = BuildConfig.API_KEY
        val LANGUAGE = "en-IN"

        val myRef4Users = FirebaseDatabase.getInstance().getReference("users")

        var MY_PREFS_NAME = "MOVIE_DB"
    }
}