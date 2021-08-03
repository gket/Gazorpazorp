package com.gketdev.gazorpazorp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.data.CharacterKeys
import com.gketdev.gazorpazorp.data.Location

@Database(entities = [Character::class, CharacterKeys::class], version = 1, exportSchema = false)
@TypeConverters(value = [AppTypeConverter::class])
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterKeysDao(): CharacterKeysDao
}