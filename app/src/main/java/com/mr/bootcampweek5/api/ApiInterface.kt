package com.mr.bootcampweek5.api


import com.mr.bootcampweek5.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}