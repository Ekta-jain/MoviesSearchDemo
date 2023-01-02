package com.e4ekta.moviessearchdemo.di

import com.e4ekta.moviessearchdemo.Constants.BASE_URL
import com.e4ekta.moviessearchdemo.MoviesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getMovieAPI(retrofit: Retrofit): MoviesAPI {
        return retrofit.create(MoviesAPI::class.java)
    }
}