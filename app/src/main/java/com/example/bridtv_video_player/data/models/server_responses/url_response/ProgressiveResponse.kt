package com.example.bridtv_video_player.data.models.server_responses.url_response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProgressiveResponse(
    @SerializedName("profile") val profile: Any,
    @SerializedName("width") val width: Any,
    @SerializedName("height") val height: Any,
    @SerializedName("mime") val mime: Any,
    @SerializedName("fps") val fps: Any,
    @SerializedName("url") val url: String,
    @SerializedName("cdn") val cdn: Any,
    @SerializedName("quality") val quality: Any,
    @SerializedName("id") val id: Any,
    @SerializedName("origin") val origin: Any,
) : Serializable