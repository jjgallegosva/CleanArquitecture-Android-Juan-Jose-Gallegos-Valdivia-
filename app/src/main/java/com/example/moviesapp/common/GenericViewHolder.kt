package com.example.moviesapp.common

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.BR
import com.example.moviesapp.R

import com.example.moviesapp.core.movie.Movie

class GenericViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val imageView: ImageView = itemView.findViewById(R.id.imgMovie)

    fun bind(item: Movie) {
        binding.setVariable(BR.obj, item)
        Glide.with(imageView.context)
            .load(item.poster_path)
            .error(R.drawable.camara)
            .into(imageView)
        binding.executePendingBindings()
    }
}