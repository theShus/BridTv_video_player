package com.example.bridtv_video_player.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.bridtv_video_player.data.models.VimeoMovie


class MovieDiffCallback : DiffUtil.ItemCallback<VimeoMovie>() {

    override fun areItemsTheSame(oldItem: VimeoMovie, newItem: VimeoMovie): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: VimeoMovie, newItem: VimeoMovie): Boolean {
        return oldItem.uri == newItem.uri
    }
}