package com.example.cores.common

import com.example.core.movie.Movie

interface TypeFactory {
    fun type(item: Movie): Int
}