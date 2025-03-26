package com.assignment.animeverse.networking

import com.assignment.animeverse.model.AnimeData
import retrofit2.http.GET

interface AnimeService {

    @GET("v4/top/anime")
    suspend fun getAnimeList():AnimeData

}