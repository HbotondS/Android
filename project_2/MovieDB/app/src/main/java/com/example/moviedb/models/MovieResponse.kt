package com.example.moviedb.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("results")
    @Expose
    var results: List<Movie>
)