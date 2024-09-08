package com.nomadicDev.pokemonGo.presentation.pokemondetails

import com.nomadicDev.pokemonGo.domain.model.Pokemon

data class PokemonDetailsState(
    val pokemon: Pokemon? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null
)
