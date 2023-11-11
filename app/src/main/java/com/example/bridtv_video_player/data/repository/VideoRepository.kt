package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.models.Car
import io.reactivex.Observable
import com.example.bridtv_video_player.data.models.Video
import com.example.bridtv_video_player.data.models.server_responses.TestModel
import com.example.bridtv_video_player.data.models.server_responses.TestResponse

interface VideoRepository {

    fun getMusicVids()

    fun testApi(): Observable<Any>


}