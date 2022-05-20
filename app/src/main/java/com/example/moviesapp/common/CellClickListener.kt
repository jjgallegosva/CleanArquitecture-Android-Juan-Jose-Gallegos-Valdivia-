package com.example.moviesapp.common

import com.example.moviesapp.core.movie.Movie


interface CellClickListener {
    fun onCellClickListener(movie: Movie)
}