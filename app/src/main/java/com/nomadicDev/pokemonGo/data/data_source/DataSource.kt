package com.nomadicDev.pokemonGo.data.data_source

import com.nomadicDev.pokemonGo.data.data_source.local.db.PokemonEntity
import com.nomadicDev.pokemonGo.data.data_source.remote.network.models.PokemonCardResponse
import com.nomadicDev.pokemonGo.network.NetworkResult

interface DataSource<T>{
    suspend fun getData() : T
}

interface NetworkDataSource : DataSource<NetworkResult<PokemonCardResponse>>

interface LocalDataSource : DataSource<List<PokemonEntity>>{
    suspend fun insertAll(pokemonCards: List<PokemonEntity>)
    suspend fun getPokemon(id: String): PokemonEntity
}