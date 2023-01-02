package com.e4ekta.moviessearchdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e4ekta.moviessearchdemo.models.MovieData
import com.e4ekta.moviessearchdemo.models.MovieRemoteKeys

// it's our database and here we
// specify entities, our version and exportSchema
@Database(entities = [MovieData::class, MovieRemoteKeys::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun quoteDao() : MovieDao
    abstract fun remoteKeysDao() : RemoteKeysDao
}