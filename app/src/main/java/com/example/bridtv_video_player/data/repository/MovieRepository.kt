package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.models.Movie
import io.reactivex.Observable

interface MovieRepository {


    fun fetchPopularMovies(): Observable<List<Movie>>


}