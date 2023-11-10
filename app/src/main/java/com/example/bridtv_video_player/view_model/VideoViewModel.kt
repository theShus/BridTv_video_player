package com.example.bridtv_video_player.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridtv_video_player.data.models.Video
import com.example.bridtv_video_player.data.repository.VideoRepository
import com.example.bridtv_video_player.states.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class VideoViewModel (private val videoRepository: VideoRepository) : ViewModel(), VideoContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override var videos: List<Video> = emptyList()


    override fun getMusicVideos() {

        println("XXX POZVANA VM FUNKCIJA")

        videoRepository.getMusicVids()
//        val subscription = videoRepository
//            .getMusicVids()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    networkState.value = NetworkState.Success(it)
//                },
//                {
//                    networkState.value = NetworkState.Error("Error happened while fetching data from the server")
//                    Timber.e(it)
//                }
//            )
//
//        subscriptions.add(subscription)

    }
}