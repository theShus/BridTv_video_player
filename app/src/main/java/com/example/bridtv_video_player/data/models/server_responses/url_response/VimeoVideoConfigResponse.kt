package com.example.bridtv_video_player.data.models.server_responses.url_response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VimeoVideoConfigResponse(
    @SerializedName("cdn_url") val cdn_url: String,
    @SerializedName("vimeo_api_url") val vimeo_api_url: String,
    @SerializedName("request") val request: RequestResponse,
    @SerializedName("player_url") val player_url: String,
    @SerializedName("video") val video: Any,
    @SerializedName("user") val user: Any,
    @SerializedName("view") val view: Int,
    @SerializedName("vimeo_url") val vimeo_url: String,
    @SerializedName("embed") val embed: Any,
    @SerializedName("seo") val seo: Any,

    ) : Serializable