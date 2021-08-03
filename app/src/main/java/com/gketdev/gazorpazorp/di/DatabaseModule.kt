package com.gketdev.gazorpazorp.di

import android.app.Application
import androidx.room.Room
import com.gketdev.gazorpazorp.database.AppTypeConverter
import com.gketdev.gazorpazorp.database.CharacterDao
import com.gketdev.gazorpazorp.database.CharacterKeysDao
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
    fun provideTypeResponseConverter(moshi: Moshi): AppTypeConverter {
        return AppTypeConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        appTypeConverter: AppTypeConverter
    ): RickAndMortyDatabase {
        return Room
            .databaseBuilder(application, RickAndMortyDatabase::class.java, "Gazorpazorp.db")
            .addTypeConverter(appTypeConverter)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(rickAndMortyDatabase: RickAndMortyDatabase): CharacterDao {
        return rickAndMortyDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterKeysDao(rickAndMortyDatabase: RickAndMortyDatabase): CharacterKeysDao {
        return rickAndMortyDatabase.characterKeysDao()
    }


}