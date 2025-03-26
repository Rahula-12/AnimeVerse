package com.assignment.animeverse.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.animeverse.DataOrException
import com.assignment.animeverse.model.AnimeData

@Preview
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
                modifier = modifier.fillMaxSize().padding(it)
            ) {
                val size= animeState.data.animeList.size
                items(size) {index->
                    Text(
                        text = animeState.data.animeList[index].title,
                        modifier=modifier.clickable {
                            onClicked(index)
                        }
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