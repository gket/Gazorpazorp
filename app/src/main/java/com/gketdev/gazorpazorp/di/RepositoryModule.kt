package com.gketdev.gazorpazorp.di

import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCharacterRepository(apiService: RickAndMortyApiService): CharacterRepository {
        return CharacterRepository(apiService)
    }
}