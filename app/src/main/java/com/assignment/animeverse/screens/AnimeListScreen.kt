package com.assignment.animeverse.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.assignment.animeverse.DataOrException
import com.assignment.animeverse.model.Anime
import com.assignment.animeverse.model.AnimeData


@Composable
fun AnimeListScreen(
    modifier: Modifier=Modifier,
    animeState:DataOrException<AnimeData> = DataOrException(),
    onClicked:(Int)->Unit={}
    ) {
    Scaffold(
        modifier = modifier.padding(top=50.dp),
        topBar = {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "AnimeVerse",
                textAlign = TextAlign.Center
            )
        }
    ) { it ->
        if (animeState.loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize().padding(it)
            ) {
                Text("Loading...")
            }
        } else if (animeState.data != null) {
            LazyColumn(
                modifier = modifier.fillMaxSize().padding(it),
                horizontalAlignment=Alignment.CenterHorizontally
            ) {
                val size= animeState.data.animeList.size
                items(size) {index->
                    AnimeListItem(
                        modifier=modifier.clickable {
                            onClicked(index)
                        },
                        animeState.data.animeList[index]

                    )
                }
            }
        }
        else {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text("Error Occurred.Please try again.")
            }
        }
    }
}

@Composable
fun AnimeListItem(modifier: Modifier=Modifier,anime: Anime) {
    Card(
        modifier = modifier.border(border = BorderStroke(20.dp, Color.Transparent),
            shape = CircleShape).wrapContentSize().padding(start = 10.dp, end=10.dp,bottom = 20.dp)
    ) {
        Column(
            modifier=modifier.padding(top=10.dp, bottom = 10.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(anime.title,modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            AsyncImage(model=anime.images.jpg.image_url, contentDescription = "${anime.title} image")
            Text("Episodes: ${anime.episodes}")
            Text("Rating: ${anime.rating}")
        }
    }
}