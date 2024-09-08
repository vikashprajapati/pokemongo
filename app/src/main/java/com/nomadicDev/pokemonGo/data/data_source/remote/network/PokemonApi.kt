package com.nomadicDev.pokemonGo.data.data_source.remote.network

import com.nomadicDev.pokemonGo.data.data_source.remote.network.models.PokemonCardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("v2/cards")
    suspend fun getCards(
        @Query("pageSize") pageSize: Int = 20): Response<PokemonCardResponse>
}