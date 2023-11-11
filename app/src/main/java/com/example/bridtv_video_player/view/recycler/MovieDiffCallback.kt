package com.example.bridtv_video_player.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.bridtv_video_player.data.models.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>(){

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {//todo ovo promeni/dodaj ako treba
        return  oldItem.id == newItem.id
    }
}