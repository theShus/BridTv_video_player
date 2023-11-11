package com.example.bridtv_video_player.view.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.databinding.MovieItemBinding


class MovieViewHolder  (private val itemBinding: MovieItemBinding, val onClick: (position: Int) -> Unit): RecyclerView.ViewHolder(itemBinding.root){

    init {
        itemBinding.root.setOnClickListener {
            onClick(layoutPosition)
        }
    }

    fun bind(car: Movie){
        itemBinding.movieName.text = "Movie name"
//        itemBinding.videoTumbnail = ""

    }


}