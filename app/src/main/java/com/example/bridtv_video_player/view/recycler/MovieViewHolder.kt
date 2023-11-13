package com.example.bridtv_video_player.view.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.databinding.MovieItemBinding
import com.squareup.picasso.Picasso


class MovieViewHolder(
    private val itemBinding: MovieItemBinding,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener {
            onClick(layoutPosition)
        }
    }

    fun bind(movie: VimeoMovie) {
        itemBinding.movieName.text = movie.name
        Picasso.get().load(movie.base_link).into(itemBinding.videoTumbnail)
    }


}