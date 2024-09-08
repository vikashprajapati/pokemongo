package com.nomadicDev.pokemonGo.domain.usecases

import com.nomadicDev.pokemonGo.data.repository.IPokemonRepository
import com.nomadicDev.pokemonGo.domain.Resource
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(): Flow<Resource<List<Pokemon>>> {
        return repository.getPokemonList()
    }
}