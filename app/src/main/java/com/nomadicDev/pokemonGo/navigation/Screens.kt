package com.nomadicDev.pokemonGo.navigation


sealed class Screens(val route: String) {
    data object Home : Screens("Home")
    data object PokemonDetails : Screens("PokemonDetails")
}