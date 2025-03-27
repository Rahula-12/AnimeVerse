package com.assignment.animeverse.networking

import com.assignment.animeverse.model.AnimeCharacters
import com.assignment.animeverse.model.AnimeData
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeService {

    @GET("v4/top/anime")
    suspend fun getAnimeList():AnimeData

    @GET("v4/anime/{animeId}/characters")
    suspend fun getAnimeCharacters(@Path("animeId") animeId:String):AnimeCharacters

}