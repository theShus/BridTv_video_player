package com.example.bridtv_video_player.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.databinding.ActivityMainBinding
import com.example.bridtv_video_player.states.NetworkState
import com.example.bridtv_video_player.view.recycler.MovieAdapter
import com.example.bridtv_video_player.view_model.MovieContract
import com.example.bridtv_video_player.view_model.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieContract.ViewModel by viewModel<MovieViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private var intent : Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.swipeContainer.isEnabled = false
        initRecycler()
        initObservers()
        movieViewModel.getVimeoMovies()
    }


    private fun initRecycler() {
        binding.movieRV.layoutManager = GridLayoutManager(this, 2)
        adapter = MovieAdapter(::onItemClick)//callback za on click
        binding.movieRV.addItemDecoration(GridItemDecoration(20)) // Adjust the spacing as needed
        binding.movieRV.adapter = adapter
    }

    private fun onItemClick(movie: VimeoMovie){
        intent = Intent(this, PlayerActivity::class.java)
        intent?.putExtra("movieName", movie.name)
        intent?.putExtra("movieDescription", movie.description)

        movieViewModel.getVideoUrl(movie.uri.split("/")[2])
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        movieViewModel.recyclerIsRefreshing.observe(this){
            binding.swipeContainer.isRefreshing = it
        }

        binding.movieRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    val itemCount = recyclerView.adapter?.itemCount//we move the recycler a bit up so it doesn't trigger again by accident
                    Handler().postDelayed(Runnable {
                        binding.movieRV.layoutManager?.scrollToPosition(itemCount!!/2 - 1)
                        movieViewModel.startRvRefresh()
                    }, 1000)

                }
            }
        })

        movieViewModel.networkState.observe(this) { networkState ->
            renderState(networkState)
        }

        movieViewModel.paginationList.observe(this) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        movieViewModel.urlToLoad.observe(this){
            if (movieViewModel.urlToLoad.value == null){
                Toast.makeText(applicationContext, "Error while opening video", Toast.LENGTH_SHORT).show()
            }
            else{
                intent?.putExtra("movieUrl", movieViewModel.urlToLoad.value)
                startActivity(intent)
            }
        }

        binding.swapVersions.setOnClickListener {
            val intent = Intent(this, RecyclerComposeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun renderState(state: NetworkState) {
        when (state) {
            is NetworkState.Success -> {
                movieViewModel.newMovies = state.movies
                movieViewModel.loadPagination()
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