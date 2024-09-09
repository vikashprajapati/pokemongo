package com.nomadicDev.pokemonGo.presentation.pokemondetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import com.nomadicDev.pokemonGo.presentation.components.ErrorView
import com.nomadicDev.pokemonGo.presentation.components.LoadingIndicator
import kotlinx.coroutines.Dispatchers

@Composable
fun PokemonDetailsScreen(
    pokemonId: String,
    viewModel: PokemonDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(PokemonDetailsEvent.GetPokemonDetails(pokemonId))
    }
    val state by viewModel.pokemonDetails.collectAsState()

    when {
        state.loading -> LoadingIndicator()
        !state.errorMessage.isNullOrEmpty() -> ErrorView(state.errorMessage!!)
        else -> state.pokemon?.let { PokemonDetailsContent(it, onNavigateBack) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsContent(pokemon: Pokemon, onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemon.name) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            PokemonImage(pokemon)
            Details(pokemon)
        }
    }
}

@Composable
fun PokemonImage(pokemon: Pokemon) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(pokemon.images.large)
            .fetcherDispatcher(Dispatchers.IO)
            .decoderDispatcher(Dispatchers.Default)
            .networkCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = pokemon.name,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentScale = ContentScale.Inside
    )
}

@Composable
fun Details(pokemon: Pokemon) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            DetailRow(label = "Level", value = pokemon.level?.toString() ?: "N/A")
        }
        item {
            DetailRow(label = "HP", value = pokemon.hp.toString())
        }
        item {
            DetailRow(label = "Types", value = pokemon.types?.joinToString(", ") ?: "N/A")
        }
        item {
            DetailRow(label = "Abilities", value = pokemon.abilities?.joinToString("\n") {"${it.name}: ${it.text}" } ?: "N/A")
        }
        item {
            DetailRow(label = "Attacks", value = pokemon.attacks.joinToString("\n") { "${it.name}: ${it.text}"  })
        }
        item {
            DetailRow(label = "Weaknesses", value = pokemon.weaknesses?.joinToString("\n") { "${it.type}: ${it.value}"  } ?: "N/A")
        }
        item {
            DetailRow(label = "Resistances", value = pokemon.resistances?.joinToString("\n") { "${it.type}: ${it.value}"  } ?:"N/A")
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}