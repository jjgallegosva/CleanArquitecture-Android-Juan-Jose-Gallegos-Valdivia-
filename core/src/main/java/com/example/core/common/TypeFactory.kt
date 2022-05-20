package com.example.core.common

import com.example.core.movie.Movie

interface TypeFactory {
    fun type(item: Movie): Int
}