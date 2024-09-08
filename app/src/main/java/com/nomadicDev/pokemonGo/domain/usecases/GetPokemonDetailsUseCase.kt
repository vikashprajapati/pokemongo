package com.nomadicDev.pokemonGo.domain.usecases

import com.nomadicDev.pokemonGo.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor( private val repository: PokemonRepository) {
    operator fun invoke(pokemonId: String) = repository.getPokemonDetails(pokemonId)
}