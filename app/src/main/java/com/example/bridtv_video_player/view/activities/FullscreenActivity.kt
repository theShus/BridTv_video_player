package com.example.bridtv_video_player.view.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.bridtv_video_player.R
import com.example.bridtv_video_player.databinding.ActivityFullscreenBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding


    private lateinit var movieUrl: String
    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
        getExtra()
        initView()
        preparePlayer()
    }

    private lateinit var fullScreenButton : FrameLayout

    private fun initListeners(){
        fullScreenButton = findViewById(R.id.exo_fullscreen_button)

        fullScreenButton.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("playbackPosition", exoPlayer!!.currentPosition)
            this.setResult(RESULT_OK, returnIntent)
            releasePlayer()
            this.finish()
        }
    }

    private fun getExtra(){
        movieUrl = intent.getStringExtra("movieUrl").toString()
        playbackPosition = intent.getLongExtra("playbackPosition", 0L)
    }

    private fun  initView(){
        supportActionBar?.hide()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        //set programmatically later
        binding.fullscreenPlayerView.layoutParams.width = 2100
        binding.fullscreenPlayerView.layoutParams.height = 900
    }

    private fun preparePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        exoPlayer!!.seekTo(playbackPosition)//set time in video
        binding.fullscreenPlayerView.player = exoPlayer
        val mediaItem = MediaItem.fromUri(movieUrl)
        exoPlayer?.setMediaItem(mediaItem)
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