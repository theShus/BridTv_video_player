package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.Paging
import com.example.bridtv_video_player.data.models.Video
import com.google.gson.annotations.SerializedName

data class GetVideosResponse(
    @SerializedName("total") var total: Int,
    @SerializedName("page") var page: Int,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("paging") var paging: Paging,
    @SerializedName("data") var data: List<Video>
)
