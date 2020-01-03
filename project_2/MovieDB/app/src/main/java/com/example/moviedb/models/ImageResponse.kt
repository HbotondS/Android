package com.example.moviedb.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageResponse(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("backdrops")
    @Expose
    var backdrops: List<Image>
)