package com.example.bridtv_video_player.data.models.server_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PagingResponse (
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
): Serializable