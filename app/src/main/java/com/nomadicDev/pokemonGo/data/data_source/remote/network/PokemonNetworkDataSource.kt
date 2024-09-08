package com.nomadicDev.pokemonGo.data.data_source.remote.network

import android.content.Context
import android.util.Log
import com.nomadicDev.pokemonGo.data.data_source.DataSource
import com.nomadicDev.pokemonGo.data.data_source.NetworkDataSource
import com.nomadicDev.pokemonGo.data.data_source.remote.network.models.PokemonCardResponse
import com.nomadicDev.pokemonGo.network.NetworkResult
import com.nomadicDev.pokemonGo.network.performCall
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PokemonNetworkDataSource  @Inject constructor(
    @ApplicationContext val context: Context,
    private val pokemonApi: PokemonApi
) : NetworkDataSource {
    private val TAG = PokemonNetworkDataSource::class.java.simpleName

    override suspend fun getData(): NetworkResult<PokemonCardResponse> {
        val response = performCall(context) {
            pokemonApi.getCards()
        }

        Log.d(TAG, "response: $response")
        return response
    }

}