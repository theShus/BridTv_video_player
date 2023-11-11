package com.example.bridtv_video_player.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bridtv_video_player.data.models.Movie
import com.example.bridtv_video_player.databinding.ActivityMainBinding
import com.example.bridtv_video_player.states.NetworkState
import com.example.bridtv_video_player.view.recycler.MovieAdapter
import com.example.bridtv_video_player.view_model.MovieContract
import com.example.bridtv_video_player.view_model.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieContract.ViewModel by viewModel<MovieViewModel>()
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initView()
        initObservers()
    }

    private fun initView() {
        binding.movieRV.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(::onItemClick)//callback za on click
        binding.movieRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.movieRV.adapter = adapter

        //todo scroll thingy 68

    }

    private fun onItemClick(movie: Movie){
        println("CLICKED ON INSTANCE")
        println(movie)
    }


    private fun initObservers() {
//        binding.button.setOnClickListener {
//            println("XXX KLIKNUTO DUGME")
//            movieViewModel.getPopularMovies()
//        }

        movieViewModel.networkState.observe(this) { networkState ->
            Timber.e(networkState.toString())
            renderState(networkState)
        }

        movieViewModel.paginationList.observe(this) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        movieViewModel.getPopularMovies()
    }

    private fun renderState(state: NetworkState) {
        when (state) {
            is NetworkState.Success -> {
                movieViewModel.movies = state.movies
                movieViewModel.loadPagination(true)
            }
            is NetworkState.Loading -> {
                println("Loading")
            }
            is NetworkState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> Timber.e("Error")
        }
    }

}
