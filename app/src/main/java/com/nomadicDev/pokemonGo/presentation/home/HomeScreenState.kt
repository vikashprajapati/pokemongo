package com.nomadicDev.pokemonGo.presentation.home

import com.nomadicDev.pokemonGo.domain.model.Pokemon

enum class SortType { LEVEL, HP }

data class PokemonHomeUiData(
    val pokemonList: List<Pokemon> = emptyList(),
    val sortType: SortType = SortType.HP,
    val errorMessage: String? = null,
    val loading : Boolean = false
)


