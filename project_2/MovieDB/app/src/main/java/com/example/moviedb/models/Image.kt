package com.example.moviedb.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image(
    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Double?,
    @SerializedName("file_path")
    @Expose
    var filePath: String?,
    @SerializedName("height")
    @Expose
    var height: Int?,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String?,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double?,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int?,
    @SerializedName("width")
    @Expose
    var width: Int?
)