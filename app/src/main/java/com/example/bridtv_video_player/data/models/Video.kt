package com.example.bridtv_video_player.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video (
    var uri: String,
    var name: String,
    var type: String,
    var link: String,
    var duration: Int,
)
