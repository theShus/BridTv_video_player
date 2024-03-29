package com.example.bridtv_video_player.states

import com.example.bridtv_video_player.data.models.VimeoMovie


sealed class NetworkState {

//    object Loading : NetworkState() could be used for some indicator

    data class Success(val movies: List<VimeoMovie>) : NetworkState()

    data class Error(val message: String) : NetworkState()

}