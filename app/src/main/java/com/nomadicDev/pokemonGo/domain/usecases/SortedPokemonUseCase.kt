package com.nomadicDev.pokemonGo.domain.usecases

import android.util.Log
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import com.nomadicDev.pokemonGo.presentation.home.SortType
import javax.inject.Inject

class SortedPokemonUseCase @Inject constructor(){
    operator fun invoke(pokemons : List<Pokemon>, sortType: SortType): List<Pokemon> {
        Log.d("SortedPokemonUseCase", "sortType: $sortType")
        val sortedPokemons = when (sortType) {
            SortType.LEVEL -> pokemons.sortedBy { it.level }
            SortType.HP -> pokemons.sortedBy { it.hp }
        }

        return sortedPokemons
    }
}