package com.example.bridtv_video_player.data.data_source

import com.example.bridtv_video_player.data.models.server_responses.GetMoviesResponse
import com.example.bridtv_video_player.data.models.server_responses.GetVimeoMoviesResponse
import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataSource {

    @GET("/3/movie/popular")
    fun fetchPopularMovies(@Query("language") language: String, @Query("page") page: Int,):Observable<GetMoviesResponse>


//    @GET("/videos/?query=movie&fields=uri, name, description, pictures&page=1&per_page=20")
//    fun fetchVimeoVideos(): Observable<GetVimeoMoviesResponse>

    @GET("/videos")
    fun fetchVimeoVideos(
        @Query("query") query: String,
        @Query("fields") fields: List<String>,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        ): Observable<GetVimeoMoviesResponse>

    //todo get vimeoVideoUrl

}