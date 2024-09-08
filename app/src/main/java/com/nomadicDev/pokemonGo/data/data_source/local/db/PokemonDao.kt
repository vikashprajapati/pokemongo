package com.nomadicDev.pokemonGo.data.data_source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonCards: List<PokemonEntity>)

    @Query("SELECT id, name, images, types, level, hp  FROM pokemon_cards")
    suspend fun getPokemons(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon_cards WHERE id = :id")
    suspend fun getPokemon(id: String): PokemonEntity
}