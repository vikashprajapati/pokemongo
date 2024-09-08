package com.nomadicDev.pokemonGo.presentation.home

sealed class HomeEvents {
    data object LoadHome: HomeEvents()
    data class SearchPokemon(val query: String): HomeEvents()
    data class SortPokemon(val sortType: SortType): HomeEvents()
}