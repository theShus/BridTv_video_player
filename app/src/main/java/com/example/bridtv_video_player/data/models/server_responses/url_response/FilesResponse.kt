package com.example.bridtv_video_player.data.models.server_responses.url_response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilesResponse (
    @SerializedName("dash") val dash: Any,
    @SerializedName("hls") val hls: Any,
    @SerializedName("progressive") val progressive: List<ProgressiveResponse>,
): Serializable