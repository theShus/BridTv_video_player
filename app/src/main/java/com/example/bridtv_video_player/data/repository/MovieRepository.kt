package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.models.VimeoMovie
import io.reactivex.Observable

interface MovieRepository {

    fun fetchVimeoVideos(pageToLoad: Int): Observable<List<VimeoMovie>>

    fun fetchVimeoVideoUrl(videoId: String): Observable<String>

}