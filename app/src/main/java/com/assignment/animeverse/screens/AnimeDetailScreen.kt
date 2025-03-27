package com.assignment.animeverse.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.assignment.animeverse.model.Anime
import com.assignment.animeverse.model.AnimeCharacters
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    modifier: Modifier,
    anime: Anime,
    animeCharacters:AnimeCharacters,
    backClick:()->Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = anime.title,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
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
            Column(
                modifier=modifier.fillMaxSize().padding(top=10.dp,bottom=10.dp).verticalScroll(rememberScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text=anime.title,modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                if(anime.trailer.youtube_id!=null) {
                    val videoId=anime.trailer.youtube_id
                    YoutubeScreen(modifier,videoId)
                }
                else {
                    AsyncImage(anime.images.webp.large_image_url,"${anime.title} image",modifier=modifier.padding(top=10.dp, bottom = 10.dp))
                }
                var genres=""
                for(genre in anime.genres){
                    genres+=genre.name+", "
                }
                var animeCharactersStr=""
                for(character in animeCharacters.data) {
                    for(voiceActor in character.voice_actors) {
                        animeCharactersStr+="${voiceActor.person.name}, "
                    }
                }
                if(animeCharactersStr.length>=3) animeCharactersStr=animeCharactersStr.substring(0..animeCharactersStr.length-3)
                genres=genres.substring(0..genres.length-3)
                Text(text = buildAnnotatedString {
                    withStyle(style= SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Synopsis: ")
                    }
                    withStyle(style= SpanStyle()) {
                        append(anime.synopsis)
                    }
                },modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text(text = buildAnnotatedString {
                    withStyle(style= SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Episodes: ")
                    }
                    withStyle(style= SpanStyle()) {
                        append(anime.episodes.toString())
                    }
                },modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text(text = buildAnnotatedString {
                    withStyle(style= SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Rating: ")
                    }
                    withStyle(style= SpanStyle()) {
                        append(anime.rating)
                    }
                },modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text(text = buildAnnotatedString {
                    withStyle(style= SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Genres: ")
                    }
                    withStyle(style= SpanStyle()) {
                        append(genres)
                    }
                },modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text(text = buildAnnotatedString {
                    withStyle(style= SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Main Cast: ")
                    }
                    withStyle(style= SpanStyle()) {
                        append(animeCharactersStr)
                    }
                },modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }

        }
    }

}

@Composable
fun YoutubeScreen(
    modifier: Modifier,
    videoId: String
) {
    AndroidView(
        modifier = modifier.padding(10.dp),
        factory = {
        val view = YouTubePlayerView(it)
        view.addYouTubePlayerListener(
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
