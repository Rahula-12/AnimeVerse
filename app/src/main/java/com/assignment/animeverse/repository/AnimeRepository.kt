package com.assignment.animeverse.repository

import com.assignment.animeverse.model.AnimeData
import com.assignment.animeverse.networking.AnimeService
import javax.inject.Inject

interface AnimeRepository {
    suspend fun getAnimeData():AnimeData
}

class AnimeRepositoryImpl @Inject constructor(private val animeService: AnimeService) : AnimeRepository{
    override suspend fun getAnimeData(): AnimeData {
        return animeService.getAnimeList()
    }
}