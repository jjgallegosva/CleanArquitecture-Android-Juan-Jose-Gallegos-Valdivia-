package com.example.moviesapp.data.network

import com.example.moviesapp.data.movie.ApiResponse
import com.example.moviesapp.data.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("upcoming")
    suspend fun getMovieList(@Query("page") page: Int,
                             @Query("api_key") api_key: String): Response<ApiResponse<MovieResponse>>
}