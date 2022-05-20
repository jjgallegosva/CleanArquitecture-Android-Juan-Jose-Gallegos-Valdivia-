package com.example.core.movie

import com.example.core.common.TypeFactory
import com.example.core.common.Visitable
import java.io.Serializable


data class Movie (
    val id:Int?=0,
    val poster_path:String?="",
    val original_title:String?="",
    val vote_average:Double?=0.0,
    val release_date:String?="",
    val overview:String?="",
    val original_language:String? = "",
    val adult:Boolean = false,
    val title: String? = "",
    val vote_count:String?=""
): Visitable, Serializable {
    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }
}