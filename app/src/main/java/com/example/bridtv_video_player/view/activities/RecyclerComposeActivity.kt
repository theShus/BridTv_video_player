package com.example.bridtv_video_player.view.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.bridtv_video_player.R
import com.example.bridtv_video_player.data.models.VimeoMovie
import com.example.bridtv_video_player.states.NetworkState
import com.example.bridtv_video_player.view_model.MovieContract
import com.example.bridtv_video_player.view_model.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecyclerComposeActivity : ComponentActivity() {

    private val movieViewModel: MovieContract.ViewModel by viewModel<MovieViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUrlObserver()

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(0xFF27272C)
            ) {


                Box {

                    TopAppBar(
                        title = { Text(text = "Jetpack compose recycler") }
                    )

                    MovieList(movieViewModel, this@RecyclerComposeActivity)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentAlignment = Alignment.BottomEnd
                    ){
                        FloatingActionButton(//THIS IS NOT HOW THIS BUTTON IS SUPPOSED TO BE USED, BUT ITS CONVENIENT RN
                            onClick = { closeActivity() },
                            containerColor = Color.Green
                        ) {
                            Image (
                                painter = painterResource(id = R.drawable.baseline_sync_24),
                                contentDescription = "fab",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            }
        }
    }
    private fun closeActivity(){ this.finish() }

    private fun setUrlObserver(){
        movieViewModel.urlToLoad.observe(this){
            if (movieViewModel.urlToLoad.value == null){
                Toast.makeText(applicationContext, "Error while opening video", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, PlayerComposeActivity::class.java)
                intent.putExtra("movieName", movieViewModel.movieName)
                intent.putExtra("movieDescription", movieViewModel.movieDescription)
                intent.putExtra("movieUrl", movieViewModel.urlToLoad.value)
                startActivity(intent)
            }
        }
    }
}



@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieList(movieViewModel: MovieContract.ViewModel, lifecycleOwner: LifecycleOwner) {
    val context = LocalContext.current
    val movieList = remember { mutableStateListOf<VimeoMovie>() }
    val lazyGridState = rememberLazyGridState()
    movieViewModel.networkState.observe(lifecycleOwner) { networkState ->
        renderState(movieViewModel, networkState, context, movieList)
    }
    movieViewModel.getVimeoMovies()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        modifier = Modifier.padding(16.dp, 70.dp, 16.dp, 16.dp)
    ) {
        itemsIndexed(movieList) { index, movie ->
            if(index == movieList.size-1){
                Toast.makeText(context, "Loading more movies", Toast.LENGTH_SHORT).show()
                movieViewModel.getVimeoMovies()
            }
            MovieListItem(movie, movieViewModel)
        }
    }
}

@Composable
fun MovieListItem(movie: VimeoMovie, movieViewModel: MovieContract.ViewModel) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(180.dp, 130.dp)
            .selectable(
                selected = true,
                onClick = {
                    Toast
                        .makeText(context, "Loading movie", Toast.LENGTH_SHORT)
                        .show()
                    movieViewModel.getVideoUrl(movie.uri.split("/")[2])
                    movieViewModel.movieName = movie.name
                    movieViewModel.movieDescription = movie.description
                }
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.base_link),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(20.dp)),
        )
        Text(
            text = movie.name,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp, 8.dp, 8.dp, 16.dp),
            color = Color.White,
        )
    }
}


private fun renderState(movieViewModel: MovieContract.ViewModel, state: NetworkState, context: Context, movieList: MutableList<VimeoMovie>) {
    when (state) {
        is NetworkState.Success -> {
            movieViewModel.newMovies = state.movies
            movieViewModel.loadPagination()

            movieList.clear()
            movieList.addAll(movieViewModel.storageList)
        }
        is NetworkState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
    }
}


