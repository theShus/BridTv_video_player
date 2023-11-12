package com.example.bridtv_video_player.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bridtv_video_player.R
import com.example.bridtv_video_player.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private lateinit var movieName: String
    private lateinit var movieDescription: String
    private lateinit var movieUrl: String

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        getExtra()
        initView()
        initListeners()
        preparePlayer()
    }

    private fun getExtra(){
        movieName = intent.getStringExtra("movieName").toString()
        movieDescription = intent.getStringExtra("movieDescription").toString()
        movieUrl = intent.getStringExtra("movieUrl").toString()
    }

    private fun initView(){
        binding.movieNameTv.text = movieName
        binding.movieDescriptionTv.text = movieDescription
    }

    private fun initListeners(){
        binding.backButton.setOnClickListener {
            this.finish()
        }
    }

    private fun preparePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer
        val mediaItem = MediaItem.fromUri(movieUrl)
//        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
//        val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
//        exoPlayer?.setMediaSource(mediaSource)

        exoPlayer?.setMediaItem(mediaItem)

        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private var isFullscreen = false


    private fun testFullscreen(){

    }


    private fun  releasePlayer(){
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

}