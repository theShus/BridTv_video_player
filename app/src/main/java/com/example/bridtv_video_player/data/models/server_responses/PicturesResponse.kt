package com.example.bridtv_video_player.data.models.server_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PicturesResponse(
    @SerializedName("uri") val uri: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("type") val type: String,
    @SerializedName("base_link") val base_link: String,
    @SerializedName("sizes") val sizes: Any,//dont need this data
) : Serializable
