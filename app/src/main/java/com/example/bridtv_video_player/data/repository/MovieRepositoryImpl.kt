package com.example.bridtv_video_player.data.repository

import com.example.bridtv_video_player.data.data_source.MovieDataSource
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.data.models.VimeoMovie
import io.reactivex.Observable

class MovieRepositoryImpl(private val remoteDataSource: MovieDataSource) : MovieRepository {

    val preSetQuery = "movie"
    val preSetFields : List<String> = listOf("","uri","name","description","pictures","")//empty strings cuz im too lazy to build a proper to string :)
    val preSetPerPage = 20

    override fun fetchPopularMovies(pageToLoad: Int): Observable<List<Movie>> {

        return remoteDataSource
            .fetchPopularMovies("en", pageToLoad)
            .map {
                it.results.map { movieResponse ->
                    Movie(
                        adult = movieResponse.adult,
                        backdrop_path = movieResponse.backdrop_path,
                        genre_ids = movieResponse.genre_ids,
                        id = movieResponse.id,
                        original_language = movieResponse.original_language,
                        original_title = movieResponse.original_title,
                        overview = movieResponse.overview,
                        popularity = movieResponse.popularity,
                        poster_path = movieResponse.poster_path,
                        release_date = movieResponse.release_date,
                        title = movieResponse.title,
                        video = movieResponse.video,
                        vote_average = movieResponse.vote_average,
                        vote_count = movieResponse.vote_count
                    )

                }
            }

    }

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

    private fun getNotNullValue(text: String?): String{
        return if(text == null) ""
        else text
    }


}