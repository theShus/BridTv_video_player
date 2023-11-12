package com.example.bridtv_video_player.data.models.server_responses.movie_response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DataResponse(
    @SerializedName("uri") val uri: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("pictures") val pictures: PicturesResponse
) : Serializable
