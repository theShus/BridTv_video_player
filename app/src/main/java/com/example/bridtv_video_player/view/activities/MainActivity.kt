package com.example.bridtv_video_player.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bridtv_video_player.databinding.ActivityMainBinding
import com.example.bridtv_video_player.view_model.VideoContract
import com.example.bridtv_video_player.view_model.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val videoViewMode: VideoContract.ViewModel by viewModel<VideoViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.a)


        init()
    }

    private fun init(){
        initView()
        initObservers()
    }

    private fun initView(){
        binding.button.setOnClickListener{
            Toast.makeText(applicationContext,"Button is clicked", Toast.LENGTH_SHORT).show()

            println("XXX KLIKNUTO DUGME")
//            videoViewMode.getMusicVideos()

        }
    }

    private fun initObservers(){
        videoViewMode.networkState.observe(this) {
            print("TRIGGEROVANO JE")
            print(it.toString())
            print(videoViewMode.networkState.value)
            Timber.e("-----")
            Timber.e(it.toString())
            Timber.e("-----")
        }
    }

}
