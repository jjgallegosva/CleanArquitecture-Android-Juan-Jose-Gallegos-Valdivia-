package com.example.cores.di

import com.example.cores.movie.GetMovieList
import com.example.cores.movie.GetMovieListImpl
import org.koin.dsl.module


val interactionModule = module {

    factory<GetMovieList> { GetMovieListImpl(get()) }
}