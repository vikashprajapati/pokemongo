package com.nomadicDev.pokemonGo.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadicDev.pokemonGo.domain.Resource
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import com.nomadicDev.pokemonGo.domain.usecases.FilterPokemonListUseCase
import com.nomadicDev.pokemonGo.domain.usecases.GetPokemonListUseCase
import com.nomadicDev.pokemonGo.domain.usecases.SortedPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val sortedPokemonUseCase: SortedPokemonUseCase,
    private val filterPokemonListUseCase: FilterPokemonListUseCase
) : ViewModel() {
    private val _uiState : MutableStateFlow<PokemonHomeUiData> = MutableStateFlow(PokemonHomeUiData())
    val uiState: StateFlow<PokemonHomeUiData> = _uiState.asStateFlow()

    private val _originalPokemonList = MutableStateFlow(emptyList<Pokemon>())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var job: Job? = null
    private var searchJob: Job? = null
    private var sortJob: Job? = null

    init {
        onEvent(HomeEvents.LoadHome)
    }

    fun onEvent(event : HomeEvents){
        when(event){
            is HomeEvents.LoadHome -> loadData()
            is HomeEvents.SearchPokemon -> { onSearchQueryChanged(event.query) }
            is HomeEvents.SortPokemon -> { onSortTypeChanged(event.sortType) }
        }
    }

    private fun loadData() {
        job?.cancel()

        job = viewModelScope.launch {
            getPokemonListUseCase().collect{
                when(it){
                    is Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true)
                    is Resource.Success -> {
                        val sortedPokemon = sortedPokemonUseCase(
                            pokemons = it.data?: emptyList(),
                            sortType = _uiState.value.sortType
                        )
                        _originalPokemonList.value = sortedPokemon
                        _uiState.value = _uiState.value.copy(loading = false, pokemonList = sortedPokemon)
                    }
                    is Resource.Error -> { _uiState.value = _uiState.value.copy(loading = false, errorMessage = it.errorMessage) }
                }
            }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.Default) {
            delay(300) // debounce time
            val filteredPokemon = filterPokemonListUseCase(pokemonList = _originalPokemonList.value, searchText = query)
            Log.d("HomeScreenViewModel", "filteredPokemon: $filteredPokemon")
            _uiState.value = _uiState.value.copy(pokemonList = filteredPokemon)
        }
    }

    private fun onSortTypeChanged(sortType: SortType) {
        _uiState.value = _uiState.value.copy(sortType = sortType)
        sortJob?.cancel()
        sortJob = viewModelScope.launch(Dispatchers.Default) {
            _uiState.value = _uiState.value.copy(loading = true)
            val sortedPokemonData = sortedPokemonUseCase(pokemons = _uiState.value.pokemonList, sortType = sortType)
            Log.d("HomeScreenViewModel", "sortedPokemonData: $sortedPokemonData")
            _uiState.value = _uiState.value.copy(pokemonList = sortedPokemonData, loading = false)
        }
    }
}
