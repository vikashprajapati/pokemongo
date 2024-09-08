package com.nomadicDev.pokemonGo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nomadicDev.pokemonGo.presentation.home.HomeScreen
import com.nomadicDev.pokemonGo.presentation.pokemondetails.PokemonDetailsScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen(
                loadPokemonDetails = {
                    navController.navigate(Screens.PokemonDetails.route + "?id=$it")
                }
            )
        }

        composable(
            route = Screens.PokemonDetails.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("id") ?: ""
            PokemonDetailsScreen(
                pokemonId = pokemonId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

