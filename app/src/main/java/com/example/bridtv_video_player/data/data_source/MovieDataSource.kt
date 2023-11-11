package com.example.bridtv_video_player.data.data_source

import com.example.bridtv_video_player.data.models.server_responses.GetMoviesResponse
import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataSource {

    @GET("/3/movie/popular")
    fun fetchPopularMovies(@Query("language") language: String, @Query("page") page: Int,):Observable<GetMoviesResponse>


}