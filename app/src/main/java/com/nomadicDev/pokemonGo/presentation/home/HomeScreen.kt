package com.nomadicDev.pokemonGo.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nomadicDev.pokemonGo.presentation.components.ErrorView
import com.nomadicDev.pokemonGo.presentation.components.LoadingIndicator
import com.nomadicDev.pokemonGo.presentation.home.components.PokemonItem
import com.nomadicDev.pokemonGo.presentation.home.components.SearchHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    loadPokemonDetails: (String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val searchText by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Pokemon Go") }) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                HomeScreenContent(
                    homeUiStateState = homeUiState,
                    searchText = searchText,
                    onSearchTextChange = { query -> viewModel.onEvent(HomeEvents.SearchPokemon(query)) },
                    sortOptionChange = { sortType ->  viewModel.onEvent(HomeEvents.SortPokemon(sortType)) },
                    onNavigateToPokemonDetails = loadPokemonDetails
                )
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    homeUiStateState: PokemonHomeUiData,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    sortOptionChange: (SortType) -> Unit,
    onNavigateToPokemonDetails: (String) -> Unit
) {
    SearchHeader(
        searchText = searchText,
        onValueChange = onSearchTextChange,
        onSortOptionChange = sortOptionChange
    )

    when {
        homeUiStateState.loading -> LoadingIndicator()

        !homeUiStateState.errorMessage.isNullOrBlank() && homeUiStateState.pokemonList.isEmpty()-> ErrorView(
            errorMessage = homeUiStateState.errorMessage,
        )

        else ->{
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(homeUiStateState.pokemonList, key = { it.id }) {
                    PokemonItem(it, onClick = { onNavigateToPokemonDetails(it.id) })
                }
            }
        }
    }
}

