package com.example.bridtv_video_player.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bridtv_video_player.R
import com.example.bridtv_video_player.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private lateinit var movieName: String
    private lateinit var movieDescription: String
    private lateinit var movieUrl: String

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    private lateinit var movieNameTv: TextView
    private lateinit var movieDescriptionTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_player)
        init()
    }

    private fun init() {
        getExtra()
        initView()
        preparePlayer()
    }

    private fun getExtra(){
        movieName = intent.getStringExtra("movieName").toString()
        movieDescription = intent.getStringExtra("movieDescription").toString()
        movieUrl = intent.getStringExtra("movieUrl").toString()
    }

    private fun initView(){
        movieNameTv = findViewById(R.id.movieNameTv)//todo ne radi binding, fix it
        movieDescriptionTv = findViewById(R.id.movieDescriptionTv)
        movieNameTv.text = movieName
        movieDescriptionTv.text = movieDescription
    }

    private fun preparePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(movieUrl)
        val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoPlayer?.setMediaSource(mediaSource)
        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
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