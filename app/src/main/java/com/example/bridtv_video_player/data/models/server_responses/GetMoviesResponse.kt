package com.example.bridtv_video_player.data.models.server_responses

import com.example.bridtv_video_player.data.models.MovieResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetMoviesResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
): Serializable