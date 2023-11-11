package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.Paging
import com.example.bridtv_video_player.data.models.Video
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class GetVideosResponse (
    var total: Int,
//    var page: Int,
//    var per_page: Int,
//    var paging: Paging,
//    var data: List<Video>
)