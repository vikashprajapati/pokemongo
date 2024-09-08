package com.nomadicDev.pokemonGo.data.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(
    AttackConverter::class,
    ResistanceConverter::class,
    WeaknessConverter::class,
    AbilityConverter::class,
    StringConverter::class,
    ImageUrlsConverter::class
)
abstract class AppDbDataSource : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "pokemon_go_db"
    }

}