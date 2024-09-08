package com.nomadicDev.pokemonGo.di

import com.nomadicDev.pokemonGo.data.data_source.LocalDataSource
import com.nomadicDev.pokemonGo.data.data_source.NetworkDataSource
import com.nomadicDev.pokemonGo.data.data_source.local.db.AppDbDataSource
import com.nomadicDev.pokemonGo.data.data_source.local.db.PokemonDbDataSource
import com.nomadicDev.pokemonGo.data.data_source.remote.network.PokemonApi
import com.nomadicDev.pokemonGo.data.data_source.remote.network.PokemonNetworkDataSource
import com.nomadicDev.pokemonGo.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ) : com.nomadicDev.pokemonGo.data.repository.IPokemonRepository {
        return PokemonRepository(networkDataSource, localDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun provideLocalDataSource(pokemonLocalDataSource: PokemonDbDataSource) : LocalDataSource

    @Singleton
    @Binds
    fun provideNetworkDataSource(pokemonNetworkDataSource: PokemonNetworkDataSource) : NetworkDataSource
}