package com.e4ekta.moviessearchdemo.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e4ekta.moviessearchdemo.models.MovieData

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieData")
    fun getQuotes(): PagingSource<Int, MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<MovieData>)

    @Query("DELETE FROM MovieData")
    suspend fun deleteQuotes()

   /*  search query will be passed through and then function will return the flow of list of movies*/
    @Query("SELECT * FROM MovieData WHERE name LIKE '%' || :search_query || '%'")
    fun searchMoviesInDatabase(search_query:String):LiveData<List<MovieData>>
}