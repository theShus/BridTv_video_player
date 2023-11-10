package com.example.bridtv_video_player.view_model

import androidx.lifecycle.LiveData
import com.example.bridtv_video_player.data.models.Video
import com.example.bridtv_video_player.states.NetworkState

interface VideoContract {
    interface ViewModel{
        val networkState: LiveData<NetworkState>
        var videos: List<Video>

        fun getMusicVideos()
    }
}