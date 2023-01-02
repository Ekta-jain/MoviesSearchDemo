package com.e4ekta.moviessearchdemo.models


import com.google.gson.annotations.SerializedName

data class ContentItems(
    @SerializedName("content")
    val content: List<MovieData>
)