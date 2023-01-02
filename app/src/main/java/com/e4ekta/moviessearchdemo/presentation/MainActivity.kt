package com.e4ekta.moviessearchdemo.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e4ekta.moviessearchdemo.R
import com.e4ekta.moviessearchdemo.paging.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var quoteViewModel: MoviesViewModel
    lateinit var adapter: MoviePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.movieList)
        val searchView = findViewById<SearchView>(R.id.search_bar)
        adapter = MoviePagingAdapter()

        val spanCount:Int = if (this.resources
                .configuration.orientation== Configuration.ORIENTATION_PORTRAIT){
            //if orientation is portrait then spanCount would be 3 else 5
            3
        } else {
            5
        }
        recyclerView.layoutManager = GridLayoutManager(this,spanCount, GridLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        quoteViewModel.list.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    searchDatabase(query)
                }
                return true
            }

        })
    }

    private fun searchDatabase(query: String) {
        /* searching from db and updating data in adapter*/
        quoteViewModel.searchMovieInDatabase(query).observe(this@MainActivity) {
            adapter.submitData(lifecycle, PagingData.from(it))
        }
    }
}