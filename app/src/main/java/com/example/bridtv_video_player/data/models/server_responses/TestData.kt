package com.example.bridtv_video_player.data.models.server_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TestData (
    @SerializedName("uri") val uri: String
): Serializable