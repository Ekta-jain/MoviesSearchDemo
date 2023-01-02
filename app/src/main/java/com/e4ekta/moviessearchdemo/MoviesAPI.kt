package com.e4ekta.moviessearchdemo

import com.e4ekta.moviessearchdemo.models.MovieRecordsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    /*On Local server I have created this API which helped to get required movie json response on basis of page No.*/
    @GET("/records")
    suspend fun getMoviesRecords(@Query("pageno") page: Int) : MovieRecordsResponse
}