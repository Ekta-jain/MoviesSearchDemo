package com.e4ekta.moviessearchdemo.paging

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.e4ekta.moviessearchdemo.db.MovieDatabase
import com.e4ekta.moviessearchdemo.MoviesAPI
import com.e4ekta.moviessearchdemo.models.MovieData
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRepository @Inject constructor(
    private val quotesAPI: MoviesAPI,
    private val quoteDatabase: MovieDatabase
) {

    fun getQuotes() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = MovieRemoteMediator(quotesAPI, quoteDatabase),
        pagingSourceFactory = { quoteDatabase.quoteDao().getQuotes() }
    ).liveData

    fun searchMovieInDatabase(searchQuery: String): LiveData<List<MovieData>> {
        return quoteDatabase.quoteDao().searchMoviesInDatabase(searchQuery)
    }
}