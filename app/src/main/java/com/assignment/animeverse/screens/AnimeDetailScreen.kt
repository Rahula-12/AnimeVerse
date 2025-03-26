package com.assignment.animeverse.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.assignment.animeverse.model.Anime

@Composable
fun AnimeDetailScreen(modifier: Modifier, anime: Anime) {
    Scaffold(
        topBar = {
            Text(text = anime.title)
        }
    ) { it->
        Card(
            shape=CardDefaults.shape,
            modifier = modifier.fillMaxSize().padding(it)
        ){
            Text(text=anime.title)
        }
    }

}