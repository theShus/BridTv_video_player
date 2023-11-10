package com.example.bridtv_video_player.states

import com.example.bridtv_video_player.data.models.Video


sealed class NetworkState {

//    object DataFetched: NetworkState()

    object Loading : NetworkState()

    data class Success(val videos: List<Video>) : NetworkState()

    data class Error(val message: String) : NetworkState()

}