package com.example.bridtv_video_player.data.data_source

import com.example.bridtv_video_player.data.models.server_responses.GetVimeoMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataSource {

    @GET("/videos")
    fun fetchVimeoVideos(
        @Query("query") query: String,
        @Query("fields") fields: List<String>,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        ): Observable<GetVimeoMoviesResponse>

    //todo get vimeoVideoUrl

}