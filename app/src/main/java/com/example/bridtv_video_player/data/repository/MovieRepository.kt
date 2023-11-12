package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.data.models.VimeoMovie
import io.reactivex.Observable

interface MovieRepository {


    fun fetchPopularMovies(pageToLoad: Int): Observable<List<Movie>>

    fun fetchVimeoVideos(pageToLoad: Int): Observable<List<VimeoMovie>>


}