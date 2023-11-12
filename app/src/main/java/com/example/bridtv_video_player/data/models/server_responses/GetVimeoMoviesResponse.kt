package com.example.bridtv_video_player.data.models.server_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetVimeoMoviesResponse (
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("paging") val paging: PagingResponse,
    @SerializedName("data") val data: List<DataResponse>,
): Serializable