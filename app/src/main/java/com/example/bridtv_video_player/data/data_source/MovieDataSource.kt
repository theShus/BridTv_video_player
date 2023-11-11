package com.example.bridtv_video_player.data.data_source

import com.example.bridtv_video_player.data.models.server_responses.GetMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDataSource {

    @GET("/3/movie/popular?language=en-US&page=1")
    fun fetchPopularMovies():Observable<GetMoviesResponse>


}