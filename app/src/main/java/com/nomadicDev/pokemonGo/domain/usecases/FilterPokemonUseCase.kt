package com.nomadicDev.pokemonGo.domain.usecases

import com.nomadicDev.pokemonGo.domain.model.Pokemon
import javax.inject.Inject

class FilterPokemonListUseCase @Inject constructor() {
    operator fun invoke(pokemonList: List<Pokemon>?, searchText: String): List<Pokemon> {
        return pokemonList?.filter { it.name.contains(searchText, ignoreCase = true) }?: emptyList()
    }
}