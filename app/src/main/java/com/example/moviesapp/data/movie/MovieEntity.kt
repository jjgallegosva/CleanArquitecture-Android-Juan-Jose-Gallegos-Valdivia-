package com.example.moviesapp.data.movie

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.moviesapp.core.movie.Movie
import com.example.moviesapp.data.network.DomainMapper

@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    var id: Int?=0,
    var poster_path:String?="",
    var original_title:String?="",
    var vote_average:Double?=0.0,
    var release_date:String?="",
    var overview:String?="",
    var original_language:String? = "",
    var adult:Boolean = false,
    var title: String? = "",
    var vote_count:String?=""
) : DomainMapper<Movie> {
    override fun mapToDomainModel(): Movie {
        return Movie(id, poster_path,original_title,vote_average,release_date,overview,original_language,adult,title,vote_count)
    }
}