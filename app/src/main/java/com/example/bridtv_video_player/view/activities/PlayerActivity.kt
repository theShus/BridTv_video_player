package com.example.bridtv_video_player.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bridtv_video_player.R
import com.example.bridtv_video_player.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var movieName: String
    private lateinit var movieDescription: String
    private lateinit var movieUrl: String
    private lateinit var fullScreenButton: FrameLayout

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

    private fun getExtra() {
        movieName = intent.getStringExtra("movieName").toString()
        movieDescription = intent.getStringExtra("movieDescription").toString()
        movieUrl = intent.getStringExtra("movieUrl").toString()
    }

    private fun initView() {
        binding.movieNameTv.text = movieName
        binding.movieDescriptionTv.text = movieDescription
        fullScreenButton = findViewById(R.id.exo_fullscreen_button)
    }

    private fun initListeners() {

        fullScreenButton.setOnClickListener {
            val intent = Intent(this, FullscreenActivity::class.java)
            intent.putExtra("movieUrl", movieUrl)
            intent.putExtra("playbackPosition", exoPlayer!!.currentPosition)
            releasePlayer()
            startActivityForResult.launch(intent)
        }

        binding.backButton.setOnClickListener {
            this.finish()
        }
    }

    private val startActivityForResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data!!
                playbackPosition = data.getLongExtra("playbackPosition", 0L)
                preparePlayer()
            }
        }

    private fun preparePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        exoPlayer!!.seekTo(playbackPosition)//set time in video
        binding.playerView.player = exoPlayer
        val mediaItem = MediaItem.fromUri(movieUrl)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private fun releasePlayer() {
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