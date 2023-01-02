package com.e4ekta.moviessearchdemo.di

import android.content.Context
import androidx.room.Room
import com.e4ekta.moviessearchdemo.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
/* this is a very important step actually
 here we used dependency injection library dagger hilt
 it actually provide our database object*/
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "quoteDB").build()
    }
}