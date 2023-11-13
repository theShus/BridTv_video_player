package com.example.bridtv_video_player.view_model

//import com.example.bridtv_video_player.data.models.Movie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.data.repository.MovieRepository
import com.example.bridtv_video_player.states.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel(),
    MovieContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    override val paginationList: MutableLiveData<List<VimeoMovie>> = MutableLiveData()
    override var newMovies: List<VimeoMovie> = arrayListOf()
    override val storageList: ArrayList<VimeoMovie> = arrayListOf()
    override var recyclerIsRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    //for opening a player activity (added for compose)
    override var urlToLoad: MutableLiveData<String> = MutableLiveData()
    override var movieName: String = ""
    override var movieDescription: String = ""

    //helper vars
    private var currentPage: Int = 1


    override fun getVimeoMovies() {
        val subscription = movieRepository
            .fetchVimeoVideos(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    networkState.value = NetworkState.Success(it)
                    currentPage += 1 //set up next load
                    recyclerIsRefreshing.value = false
                    println("CURRENT LIST SIZE = " + storageList.size)
                },
                {
                    networkState.value =
                        NetworkState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getVideoUrl(videoId: String) {
        val subscription = movieRepository
            .fetchVimeoVideoUrl(videoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    urlToLoad.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun startRvRefresh() {
        recyclerIsRefreshing.value = true
        getVimeoMovies()
    }

    override fun loadPagination() {
        //its loading perfectly 20 movies every pull, so no need for special pagination
        storageList.addAll(newMovies)
        paginationList.value = storageList
    }
}