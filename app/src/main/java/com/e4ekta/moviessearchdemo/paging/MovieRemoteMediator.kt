package com.e4ekta.moviessearchdemo.paging
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.e4ekta.moviessearchdemo.db.MovieDatabase
import com.e4ekta.moviessearchdemo.MoviesAPI
import com.e4ekta.moviessearchdemo.models.MovieData
import com.e4ekta.moviessearchdemo.models.MovieRemoteKeys
import java.lang.Exception
/** RemoteMediator helped to Defines a set of callbacks used to incrementally load data from a remote source into a local source wrapped by a PagingSource **/
@ExperimentalPagingApi
class MovieRemoteMediator(
    private val movieAPI: MoviesAPI,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, MovieData>() {

    val quoteDao = movieDatabase.quoteDao()
    val quoteRemoteKeysDao = movieDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieData>): MediatorResult {
        return try {

            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    /**   First Time refresh then this lodeType will call */
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1

                }
                LoadType.PREPEND -> {
                    /** to get prevPage when user scroll down*/
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    /** to get nextPage when user scroll down*/
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = movieAPI.getMoviesRecords(currentPage)
            val endOfPaginationReached = response.page.pageSize.toInt() == currentPage

            val prevPage = if(currentPage == 1) 0 else currentPage -1
            val nextPage = if(endOfPaginationReached) 0 else currentPage + 1

            movieDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    quoteDao.deleteQuotes()
                    quoteRemoteKeysDao.deleteAllRemoteKeys()
                }

                quoteDao.addQuotes(response.page.contentItems.content)
                val keys = response.page.contentItems.content.map { movieData ->
                    MovieRemoteKeys(
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                quoteRemoteKeysDao.addAllRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)
        }
        catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieData>
    ): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?._id?.let { id ->
                quoteRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MovieData>
    ): MovieRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movieData ->
                quoteRemoteKeysDao.getRemoteKeys(id = movieData._id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MovieData>
    ): MovieRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movieData ->
                quoteRemoteKeysDao.getRemoteKeys(id = movieData._id)
            }
    }
}







