package com.example.core.di

import com.example.core.movie.GetMovieList
import com.example.core.movie.GetMovieListImpl
import org.koin.dsl.module

val interactionModule = module {

    factory<GetMovieList> { GetMovieListImpl(get()) }
}