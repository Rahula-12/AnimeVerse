package com.assignment.animeverse.repository

import com.assignment.animeverse.model.AnimeCharacters
import com.assignment.animeverse.model.AnimeData
import com.assignment.animeverse.networking.AnimeService
import javax.inject.Inject

interface AnimeRepository {
    suspend fun getAnimeData():AnimeData
    suspend fun getAnimeCharacters(animeId:String):AnimeCharacters
}

class AnimeRepositoryImpl @Inject constructor(private val animeService: AnimeService) : AnimeRepository{
    override suspend fun getAnimeData(): AnimeData {
        return animeService.getAnimeList()
    }

    override suspend fun getAnimeCharacters(animeId: String): AnimeCharacters {
        return animeService.getAnimeCharacters(animeId)
    }
}