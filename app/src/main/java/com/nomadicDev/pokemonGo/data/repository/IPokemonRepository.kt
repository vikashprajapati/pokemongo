package com.nomadicDev.pokemonGo.data.repository

import com.nomadicDev.pokemonGo.domain.Resource
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getPokemonList(): Flow<Resource<List<Pokemon>>>
    fun getPokemonDetails(id: String): Flow<Resource<Pokemon>>
}