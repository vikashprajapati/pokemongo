package com.nomadicDev.pokemonGo.presentation.pokemondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadicDev.pokemonGo.domain.Resource
import com.nomadicDev.pokemonGo.domain.usecases.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel@Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {
    private val _pokemonDetails: MutableStateFlow<PokemonDetailsState> = MutableStateFlow(PokemonDetailsState())
    val pokemonDetails: StateFlow<PokemonDetailsState> = _pokemonDetails

    fun onEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.GetPokemonDetails -> {
                getPokemonDetails(event.pokemonId)
            }
        }
    }

    private fun getPokemonDetails(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailsUseCase(pokemonId).collect{ pokemon ->
                when (pokemon) {
                    is Resource.Success -> {
                        _pokemonDetails.value = PokemonDetailsState(pokemon = pokemon.data, loading = false)
                    }
                    is Resource.Error -> {
                        _pokemonDetails.value = PokemonDetailsState(errorMessage = pokemon.errorMessage,  loading = false)
                    }
                    is Resource.Loading -> {
                        _pokemonDetails.value = PokemonDetailsState(loading = true)
                    }
                }
            }
        }
    }
}

sealed class PokemonDetailsEvent {
    data class GetPokemonDetails(val pokemonId : String) : PokemonDetailsEvent()
}
