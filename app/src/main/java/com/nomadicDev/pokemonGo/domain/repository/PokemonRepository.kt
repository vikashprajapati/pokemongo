package com.nomadicDev.pokemonGo.domain.repository

import android.util.Log
import com.nomadicDev.pokemonGo.data.data_source.LocalDataSource
import com.nomadicDev.pokemonGo.data.data_source.NetworkDataSource
import com.nomadicDev.pokemonGo.data.repository.IPokemonRepository
import com.nomadicDev.pokemonGo.domain.Resource
import com.nomadicDev.pokemonGo.domain.model.Pokemon
import com.nomadicDev.pokemonGo.domain.toPokemonCardEntities
import com.nomadicDev.pokemonGo.domain.toDomainModel
import com.nomadicDev.pokemonGo.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : IPokemonRepository {
    private val TAG: String = IPokemonRepository::class.java.simpleName

    override fun getPokemonList(): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        Log.d(TAG, "getPokemonList loading")

        val localPokemonList = localDataSource.getData()
        Log.d(TAG, "getPokemonList localPokemonList: $localPokemonList")
        if(localPokemonList.isNotEmpty()){
            emit(Resource.Success(localPokemonList.toDomainModel()))
            Log.d(TAG, "localPokemonList success")
        }

        networkDataSource.getData().let { networkResult ->
            when(networkResult){
                is NetworkResult.Success -> {
                    Log.d(TAG, "networkResult: ${networkResult.data}")
                    val pokemonEntities = networkResult.data?.data?.toPokemonCardEntities()?: localPokemonList
                    localDataSource.insertAll(pokemonEntities)
                    emit(Resource.Success(pokemonEntities.toDomainModel()))
                    Log.d(TAG, "network success")
                }
                is NetworkResult.Error -> {
                    emit(Resource.Error(networkResult.message))
                    Log.d(TAG, "network error")
                }
            }
        }

    }.flowOn(Dispatchers.IO)

    override fun getPokemonDetails(id: String): Flow<Resource<Pokemon>> = flow {
        try {
            emit(Resource.Loading())
            val pokemon = localDataSource.getPokemon(id)
            Log.d(TAG, "getPokemonDetails: id:$id and $pokemon")
            emit(Resource.Success(pokemon.toDomainModel()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

}