package com.e4ekta.moviessearchdemo.presentation

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.e4ekta.moviessearchdemo.paging.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val list = repository.getQuotes().cachedIn(viewModelScope)

    fun searchMovieInDatabase(searchQuery: String): LiveData<List<com.e4ekta.moviessearchdemo.models.MovieData>> {
        return repository.searchMovieInDatabase(searchQuery)
    }
}