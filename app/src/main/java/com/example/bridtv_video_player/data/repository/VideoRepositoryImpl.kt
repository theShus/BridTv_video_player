package com.example.bridtv_video_player.data.repository

import android.annotation.SuppressLint
import com.example.bridtv_video_player.data.data_source.VideoDataSource
import com.example.bridtv_video_player.data.models.Car
import com.example.bridtv_video_player.data.models.Video
import com.example.bridtv_video_player.data.models.server_responses.GetVideosResponse
import com.example.bridtv_video_player.data.models.server_responses.TestModel
import com.example.bridtv_video_player.data.models.server_responses.TestResponse
import io.reactivex.Observable

class VideoRepositoryImpl(private val remoteDataSource: VideoDataSource) : VideoRepository {

//    lateinit var test: Observable<any>



    @SuppressLint("CheckResult")
    override fun getMusicVids(){
        println("XXX POZVANA REPO FUNKCIJA")


//        remoteDataSource
//            .fetchMusic()
//            .map {
//                println(it)

//                it.data.map { videoResponse ->
//
//                    Video(
//                        uri = videoResponse.uri,
//                        name = videoResponse.name,
//                        type = videoResponse.type,
//                        link = videoResponse.link,
//                        duration = videoResponse.duration
//                    )
//                }
//            }
    }

    override fun testApi(): Observable<Any>{
        println("xxx PRE KURCA")
//        println(remoteDataSource.fetchAll())

        return remoteDataSource
            .fetchAll()
//            .map {
//                it.cars.map { carResponse ->
//                    Car(
//                        id = carResponse.id,
//                        car = carResponse.car,
//                        car_model = carResponse.car_model,
//                        car_color = carResponse.car_color,
//                        car_model_year = carResponse.car_model_year,
//                        car_vin = carResponse.car_vin,
//                        price = carResponse.price,
//                        availability = carResponse.availability,
//                    )
//                }
//            }

    }


}