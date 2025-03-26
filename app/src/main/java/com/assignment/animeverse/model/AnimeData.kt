package com.assignment.animeverse.model

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("data")
    val animeList: List<Anime>,
    val pagination: Pagination?
)