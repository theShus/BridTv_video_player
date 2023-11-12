package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.data_source.MovieDataSource
import com.example.bridtv_video_player.data.models.VimeoMovie
import io.reactivex.Observable

class MovieRepositoryImpl(private val remoteDataSource: MovieDataSource) : MovieRepository {

    val preSetQuery = "music"
    val preSetFields : List<String> = listOf("","uri","name","description","pictures","")//empty strings cuz im too lazy to build a proper to string :)
    val preSetPerPage = 20

    override fun fetchVimeoVideos(pageToLoad: Int): Observable<List<VimeoMovie>> {
        return remoteDataSource
            .fetchVimeoVideos(preSetQuery, preSetFields, pageToLoad, preSetPerPage)
            .map {
                it.data.map { moviesResponse ->
                    VimeoMovie(
                        uri = getNotNullValue(moviesResponse.uri),
                        name = getNotNullValue(moviesResponse.name),
                        description = getNotNullValue(moviesResponse.description),
                        base_link = getNotNullValue(moviesResponse.pictures.base_link),
                    )
                }
            }
    }

    override fun fetchVimeoVideoUrl(videoId: String): Observable<String> {
        return remoteDataSource
            .fetchVideoUrl("http://player.vimeo.com/video/$videoId/config")
            .map { it.request.files.progressive[0].url }
    }

    private fun getNotNullValue(text: String?): String{
        return if(text == null) ""
        else text
    }

}