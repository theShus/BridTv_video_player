package com.example.bridtv_video_player.view_model

import androidx.lifecycle.ViewModel
import com.example.bridtv_video_player.data.repository.VideoRepository

class VideoViewModel (private val videoRepository: VideoRepository) : ViewModel(), VideoContract.ViewModel{

    override val test = ""
}