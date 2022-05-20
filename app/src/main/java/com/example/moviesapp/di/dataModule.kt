package com.example.moviesapp.di

import com.example.moviesapp.core.movie.GetMovieList
import com.example.moviesapp.core.movie.GetMovieListImpl
import org.koin.dsl.module


val dataModule = module {

    factory<GetMovieList> { GetMovieListImpl(get()) }
}
