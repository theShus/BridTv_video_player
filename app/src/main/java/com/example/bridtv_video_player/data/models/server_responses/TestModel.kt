package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.Paging
import com.example.bridtv_video_player.data.models.Video
//import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class TestModel (
    var path: String,
    var methods: List<String>
)