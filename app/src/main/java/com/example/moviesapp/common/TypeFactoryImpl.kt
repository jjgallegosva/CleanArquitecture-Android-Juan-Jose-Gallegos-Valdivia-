package com.example.moviesapp.common

import com.dnegu.core.common.TypeFactory
import com.example.moviesapp.R
import com.example.moviesapp.core.movie.Movie

class TypeFactoryImpl : TypeFactory {
    override fun type(item: Movie): Int {
        return R.layout.row_movie
    }
}