package com.dnegu.core.common

import com.example.moviesapp.core.movie.Movie


interface TypeFactory {
    fun type(item: Movie): Int
}