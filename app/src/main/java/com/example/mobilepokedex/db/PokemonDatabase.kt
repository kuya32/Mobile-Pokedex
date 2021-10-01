package com.example.mobilepokedex.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilepokedex.data.remote.responses.Pokemon

@Database(entities = arrayOf(Pokemon::class), version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    
}