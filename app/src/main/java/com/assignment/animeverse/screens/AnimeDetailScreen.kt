package com.assignment.animeverse.screens

import android.webkit.WebView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.assignment.animeverse.model.Anime
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(modifier: Modifier, anime: Anime,backClick:()->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = anime.title)
                },
                navigationIcon={
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back button",
                        modifier=modifier.clickable {
                            backClick()
                        }
                    )
                }
            )
        },

    ) { it->
        Card(
            shape=CardDefaults.shape,
            modifier = modifier.padding(it).fillMaxSize().padding(start = 10.dp, end = 10.dp).clip(RoundedCornerShape(5))
        ){
            Text(text=anime.title,modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            anime.trailer.youtube_id?.let { video_id->
                YoutubeScreen(modifier,video_id)
            }
        }
    }

}

@Composable
fun YoutubeScreen(
    modifier: Modifier,
    videoId: String
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}
