package com.example.bridtv_video_player.modules

import com.example.bridtv_video_player.data.data_source.MovieDataSource
import com.example.bridtv_video_player.data.repository.MovieRepository
import com.example.bridtv_video_player.data.repository.MovieRepositoryImpl
import com.example.bridtv_video_player.view_model.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movie_module = module {

    viewModel { MovieViewModel(movieRepository = get()) }

    single<MovieRepository> { MovieRepositoryImpl(remoteDataSource = get()) }

    single<MovieDataSource> { create(retrofit = get()) }

}