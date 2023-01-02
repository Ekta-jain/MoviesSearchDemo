package com.e4ekta.moviessearchdemo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// it's our data class that we have
// annotate @Entity to create it as table
@Entity(tableName = "MovieData")
data class MovieData(
    // we make id as primary
    // key and it will autogenerate
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val posterImage: String
)