package com.example.moviesapp.core.movie
import com.example.moviesapp.core.common.Result
interface MovieRepository {
    suspend fun getMovieList(id: Int): Result<List<Movie>>

    //suspend fun getMovieById(id: Int): Result<Movie>
}