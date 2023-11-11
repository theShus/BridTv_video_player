package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.CarResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetCarsResponse(
    @SerializedName("cars") val cars: List<CarResponse>
): Serializable
