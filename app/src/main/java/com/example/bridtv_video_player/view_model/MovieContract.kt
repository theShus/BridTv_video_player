package com.example.bridtv_video_player.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.states.NetworkState

interface MovieContract {
    interface ViewModel{
        val networkState: LiveData<NetworkState>
        var newMovies: List<VimeoMovie>
        val paginationList: MutableLiveData<List<VimeoMovie>>

        fun getVimeoMovies()

        fun loadPagination(initial: Boolean)

    }
}