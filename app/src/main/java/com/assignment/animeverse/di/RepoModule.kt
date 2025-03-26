package com.assignment.animeverse.di

import com.assignment.animeverse.repository.AnimeRepository
import com.assignment.animeverse.repository.AnimeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepoModule {
    @Binds
    abstract fun bindsAnimeRepo(animeRepositoryImpl: AnimeRepositoryImpl):AnimeRepository

}