package com.example.bridtv_video_player.view.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.viewpager.widget.ViewPager
import com.example.bridtv_video_player.databinding.ActivityMainBinding
import com.example.bridtv_video_player.ui.theme.BridTv_video_playerTheme
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
            videoViewMode.getMusicVideos()

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
