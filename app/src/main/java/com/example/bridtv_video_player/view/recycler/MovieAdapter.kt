package com.example.bridtv_video_player.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.databinding.MovieItemBinding

class MovieAdapter(val onClick: (stock: VimeoMovie) -> Unit) :
    ListAdapter<VimeoMovie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding) {
            onClick(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}