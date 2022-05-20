package com.example.moviesapp.data.di


import com.example.moviesapp.core.movie.MovieRepository
import com.example.moviesapp.data.movie.MovieRepositoryImpl
import com.example.moviesapp.data.utils.NetworkUtils
import com.example.moviesapp.data.utils.NetworkUtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import com.example.moviesapp.data.utils.LocalStorage

val repositoryModule = module {
    single { LocalStorage(androidContext()) }

    factory<NetworkUtils> { NetworkUtilsImpl(androidContext()) }
    factory<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}