package com.nomadicDev.pokemonGo.data.data_source.local.db

import com.nomadicDev.pokemonGo.data.data_source.LocalDataSource
import javax.inject.Inject

class PokemonDbDataSource @Inject constructor(private val pokemonDao: PokemonDao) : LocalDataSource {

    override suspend fun insertAll(pokemonCards: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemonCards)
    }

    override suspend fun getPokemon(id: String): PokemonEntity {
        return pokemonDao.getPokemon(id)
    }

    override suspend fun getData(): List<PokemonEntity> {
        return pokemonDao.getPokemons()
    }
}