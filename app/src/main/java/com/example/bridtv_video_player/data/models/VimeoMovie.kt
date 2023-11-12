package com.example.bridtv_video_player.data.models

import com.google.gson.annotations.SerializedName


data class VimeoMovie(
    val uri: String,
    val name: String,
    val description: String,
    val base_link: String
)
