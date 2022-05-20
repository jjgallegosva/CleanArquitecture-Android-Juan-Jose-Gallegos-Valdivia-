package com.example.moviesapp.ui.movies

import com.example.moviesapp.common.BaseViewModel
import com.example.moviesapp.common.Success
import com.example.moviesapp.common.Error
import com.example.moviesapp.core.common.onSuccess
import com.example.moviesapp.core.common.onFailure
import com.example.moviesapp.core.movie.GetMovieList
import com.example.moviesapp.core.movie.Movie


class MovieListViewModel (private val getMovieList: GetMovieList):
    BaseViewModel<List<Movie>, Any>() {
    fun getAll(id: Int) = executeUseCase {
        getMovieList(id)
            .onSuccess { state.value = Success(it) }
            .onFailure { state.value = Error(it.throwable) }
    }
}