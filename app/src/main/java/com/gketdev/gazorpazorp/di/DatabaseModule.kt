package com.gketdev.gazorpazorp.di

import android.app.Application
import androidx.room.Room
import com.gketdev.gazorpazorp.database.AppTypeConverter
import com.gketdev.gazorpazorp.database.CharacterDao
import com.gketdev.gazorpazorp.database.RickAndMortyDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): RickAndMortyDatabase {
        return Room
            .databaseBuilder(application, RickAndMortyDatabase::class.java, "Gazorpazorp.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(rickAndMortyDatabase: RickAndMortyDatabase): CharacterDao {
        return rickAndMortyDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): AppTypeConverter {
        return AppTypeConverter(moshi)
    }
}