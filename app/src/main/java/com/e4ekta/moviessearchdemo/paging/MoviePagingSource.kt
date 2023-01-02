package com.e4ekta.moviessearchdemo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.e4ekta.moviessearchdemo.MoviesAPI
import java.lang.Exception

const val STARTING_PAGE_INDEX = 1

class QuotePagingSource(private val quotesAPI: MoviesAPI) : PagingSource<Int, com.e4ekta.moviessearchdemo.models.MovieData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.e4ekta.moviessearchdemo.models.MovieData> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = quotesAPI.getMoviesRecords(position)

            return LoadResult.Page(
                data = response.page.contentItems.content,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == response.page.pageSize.toInt()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.e4ekta.moviessearchdemo.models.MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}