package com.example.core.movie

interface MovieRepository {
    suspend fun getMovieList(id: Int): Result<List<Movie>>

    suspend fun getMovieById(id: Int): Result<Movie>
}