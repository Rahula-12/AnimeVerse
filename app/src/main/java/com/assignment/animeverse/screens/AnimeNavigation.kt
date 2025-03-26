package com.assignment.animeverse.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.assignment.animeverse.DataOrException
import com.assignment.animeverse.model.AnimeData

enum class AnimeScreen{
    AnimeListScreen,AnimeDetailScreen
}

@Composable
fun AnimaNavigation(
    modifier: Modifier,
    animeState:DataOrException<AnimeData>
) {
    val navController= rememberNavController()
    NavHost(
        navController=navController,
        startDestination = AnimeScreen.AnimeListScreen.name) {
        composable(AnimeScreen.AnimeListScreen.name) {
            AnimeListScreen(modifier,animeState){index->
                navController.navigate(AnimeScreen.AnimeDetailScreen.name+"/${index}")
            }
        }
        composable(AnimeScreen.AnimeDetailScreen.name+"/{animeId}",arguments = listOf(navArgument("animeId"){
            type = NavType.IntType
        })) {backStackEntry->
            val animeId = backStackEntry.arguments?.getInt("animeId")
            animeId?.let {
                AnimeDetailScreen(modifier,animeState.data!!.animeList[animeId]){
                    navController.popBackStack()
                }
            }
        }
    }
}