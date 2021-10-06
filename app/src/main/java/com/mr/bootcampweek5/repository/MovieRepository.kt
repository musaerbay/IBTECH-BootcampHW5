package com.mr.bootcampweek5.repository

import com.mr.bootcampweek5.api.RetrofitClient
import com.mr.bootcampweek5.db.MovieDatabase
import com.mr.bootcampweek5.model.Result

class MovieRepository(val db: MovieDatabase) {

    suspend fun getPopularMovie(apiKey:String,page: Int) = RetrofitClient.getApi
        .getPopularMovie(apiKey,page)

    suspend fun insert(result: Result)=db.movieDao().insert(result)

    fun getAllMovie()=db.movieDao().getAllMovies()

    suspend fun delete(result: Result)=db.movieDao().delete(result)
}