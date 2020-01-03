package com.example.moviedb.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoResponse(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("results")
    @Expose
    var results: List<Video>
)