package com.assignment.animeverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.animeverse.screens.AnimaNavigation
import com.assignment.animeverse.ui.theme.AnimeVerseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<AnimeViewModel>()
        enableEdgeToEdge()
        setContent {
            val animeState=viewModel.animeState.collectAsStateWithLifecycle()
            val animeCharacters=viewModel.animeCharacters.collectAsStateWithLifecycle()
            AnimeVerseTheme {
                AnimaNavigation(Modifier,animeState.value,animeCharacters.value){
                    viewModel.animeCharacters(it)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AnimeVerseTheme {
//        Greeting("Android")
//    }
//}