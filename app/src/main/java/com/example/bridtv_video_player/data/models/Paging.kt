package com.example.bridtv_video_player.data.models

import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class Paging (
    var next: String,
    var previous: String,
    var first: String,
    var last: String
)