package com.example.bridtv_video_player.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.data.repository.MovieRepository
import com.example.bridtv_video_player.states.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel(), MovieContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    override val paginationList: MutableLiveData<List<VimeoMovie>> = MutableLiveData()
    override var newMovies: List<VimeoMovie> = arrayListOf()

    //helper var
    private val storageList: ArrayList<VimeoMovie> = arrayListOf()
    private var currentPage: Int = 1

    override fun getVimeoMovies() {
        println("XXX POZIVAMO VIMEO KURAC")

        val subscription = movieRepository
            .fetchVimeoVideos(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println("XXX VIMEOOO")
                    println(it)
                    networkState.value = NetworkState.Success(it)
                    currentPage += 1 //set up next load
                },
                {
                    networkState.value = NetworkState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun loadPagination(initial: Boolean) {
        //its loading perfectly 20 movies every pull, so no need for special pagination
        storageList.addAll(newMovies)
        paginationList.value = storageList
    }
}