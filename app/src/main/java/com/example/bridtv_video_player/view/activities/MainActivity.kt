package com.example.bridtv_video_player.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.databinding.ActivityMainBinding
import com.example.bridtv_video_player.states.NetworkState
import com.example.bridtv_video_player.view.recycler.MovieAdapter
import com.example.bridtv_video_player.view_model.MovieContract
import com.example.bridtv_video_player.view_model.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieContract.ViewModel by viewModel<MovieViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
//        initRecycler()
        initObservers()
    }


    private fun initRecycler() {
        binding.movieRV.layoutManager = GridLayoutManager(this, 2)
        adapter = MovieAdapter(::onItemClick)//callback za on click
        binding.movieRV.addItemDecoration(GridItemDecoration(20)) // Adjust the spacing as needed
        binding.movieRV.adapter = adapter

        binding.movieRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
//                    movieViewModel.getPopularMovies()
                    Toast.makeText(applicationContext, "Loading more movies", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun onItemClick(movie: Movie){
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("movieName", movie.original_title)
        intent.putExtra("movieDescription", movie.overview)
        //todo postavi pravi link
        intent.putExtra(
            "movieUrl", "https://vod-progressive.akamaized.net/exp=1699785275~acl=%2Fvimeo-transcode-storage-prod-us-east1-h264-540p%2F01%2F4190%2F18%2F470951878%2F2096012386.mp4~hmac=f82f946dbbdd293263b2ee5be11393b7d004279e5d548b75a3883186b8dd6513/vimeo-transcode-storage-prod-us-east1-h264-540p/01/4190/18/470951878/2096012386.mp4")
        startActivity(intent)
        finish()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        movieViewModel.networkState.observe(this) { networkState ->
            Timber.e(networkState.toString())
            renderState(networkState)
        }

        movieViewModel.paginationList.observe(this) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

//        movieViewModel.getPopularMovies()
        movieViewModel.getVimeoMovies()
    }

    private fun renderState(state: NetworkState) {
        when (state) {
            is NetworkState.Success -> {
                movieViewModel.newMovies = state.movies
                movieViewModel.loadPagination(true)
            }
            is NetworkState.Loading -> {
                println("Loading")
            }
            is NetworkState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class GridItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spacing
        outRect.right = spacing
        outRect.top = spacing
        outRect.bottom = spacing
    }
}