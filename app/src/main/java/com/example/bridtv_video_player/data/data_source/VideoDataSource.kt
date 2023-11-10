package com.example.bridtv_video_player.data.data_source

import com.example.bridtv_video_player.data.models.server_responses.GetVideosResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VideoDataSource {

    @GET("videos/?query=music&fields=uri, name, type, link, duration")//todo postavi bolji url
    fun fetchMusic(): Observable<GetVideosResponse>

}