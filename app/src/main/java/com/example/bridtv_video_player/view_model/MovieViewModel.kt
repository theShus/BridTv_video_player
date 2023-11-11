package com.example.bridtv_video_player.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.data.repository.MovieRepository
import com.example.bridtv_video_player.states.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel(), MovieContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    override val paginationList: MutableLiveData<List<Movie>> = MutableLiveData()

    override var movies: List<Movie> = arrayListOf()


    override fun getPopularMovies() {
        val subscription = movieRepository
            .fetchPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println(it)
                    networkState.value = NetworkState.Success(it)
                },
                {
                    networkState.value = NetworkState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun loadPagination(initial: Boolean) {
        val holderList: ArrayList<Movie> = arrayListOf()

        for (i in 0 until 10){
            holderList.add(movies[i])
        }

        paginationList.value = holderList
    }
}