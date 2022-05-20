package com.example.moviesapp.di

import com.example.moviesapp.core.movie.GetMovieList
import com.example.moviesapp.core.movie.GetMovieListImpl
import com.example.moviesapp.ui.login.LoginViewModel
import com.example.moviesapp.ui.movies.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModule = module {

    factory<GetMovieList> { GetMovieListImpl(get()) }
}
