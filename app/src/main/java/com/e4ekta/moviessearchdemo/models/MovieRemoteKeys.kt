package com.e4ekta.moviessearchdemo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieRemoteKeys(
    @PrimaryKey ( autoGenerate = true)
    val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)