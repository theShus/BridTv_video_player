package com.example.bridtv_video_player.view.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.databinding.MovieItemBinding
import com.squareup.picasso.Picasso


class MovieViewHolder  (private val itemBinding: MovieItemBinding, val onClick: (position: Int) -> Unit): RecyclerView.ViewHolder(itemBinding.root){

    val BASE_IMG_URL: String = "https://image.tmdb.org/t/p/original"

    init {
        itemBinding.root.setOnClickListener {
            onClick(layoutPosition)
        }
    }

    fun bind(movie: Movie){
        itemBinding.movieName.text = movie.original_title
        Picasso.get().load(BASE_IMG_URL + movie.backdrop_path).into(itemBinding.videoTumbnail)
    }


}