package com.e4ekta.moviessearchdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e4ekta.moviessearchdemo.models.MovieRemoteKeys

/* here we have our Dao having three
 different queries as follows.....*/
@Dao
interface RemoteKeysDao {
    // it's basically for reading our database
    @Query("SELECT * FROM MovieRemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): MovieRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MovieRemoteKeys>)

    @Query("DELETE FROM MovieRemoteKeys")
    suspend fun deleteAllRemoteKeys()
}