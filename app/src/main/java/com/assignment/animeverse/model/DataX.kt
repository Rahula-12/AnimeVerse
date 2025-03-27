package com.assignment.animeverse.model

data class DataX(
    val character: Character,
    val favorites: Int,
    val role: String,
    val voice_actors: List<VoiceActor>
)