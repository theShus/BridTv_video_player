package com.example.bridtv_video_player.view.activities

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bridtv_video_player.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource

class PlayerComposeActivity : ComponentActivity() {

    private lateinit var movieName: String
    private lateinit var movieDescription: String
    private lateinit var movieUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieName = intent.getStringExtra("movieName").toString()
        movieDescription = intent.getStringExtra("movieDescription").toString()
        movieUrl = intent.getStringExtra("movieUrl").toString()


        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF27272C)
            ) {

                Box() {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(400.dp, 300.dp)
                        ) {
                            VideoPlayer(movieUrl)
                        }

                        Text(
                            text = "Title:\n$movieName",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 130.dp),
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Description:\n$movieDescription",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        contentAlignment = Alignment.BottomStart
                    ){
                        FloatingActionButton(//THIS IS NOT HOW THIS BUTTON IS SUPPOSED TO BE USED, BUT ITS CONVENIENT RN
                            onClick = { closeActivity() },
                            containerColor = Color.LightGray
                        ) {
                            Image (
                                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                contentDescription = "fab",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            }
        }
    }

    private fun closeActivity() { this.finish() }
}

@Composable
private fun VideoPlayer(movieUrl: String) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val defaultDataSourceFactory = DefaultDataSource.Factory(context)

            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                context,
                defaultDataSourceFactory
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                MediaItem.fromUri(movieUrl)
            )

            setMediaSource(source)
            prepare()
        }
    }

    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE


    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        })
    ) {
        onDispose { exoPlayer.release() }
    }
}




