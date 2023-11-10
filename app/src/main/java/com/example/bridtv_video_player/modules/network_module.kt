package com.example.bridtv_video_player.modules

import com.example.bridtv_video_player.data.data_source.VideoDataSource
import com.example.bridtv_video_player.data.repository.VideoRepository
import com.example.bridtv_video_player.data.repository.VideoRepositoryImpl
import com.example.bridtv_video_player.view_model.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val video_module = module {

    viewModel { VideoViewModel (videoRepository = get()) }

    single<VideoRepository> { VideoRepositoryImpl(remoteDataSource = get()) }

    single<VideoDataSource> { create(retrofit = get()) }

}