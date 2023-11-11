package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.Paging
import com.example.bridtv_video_player.data.models.Video
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class TestResponse (
    var endpoints: List<TestModel>
)