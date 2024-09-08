package com.nomadicDev.pokemonGo.di

import android.app.Application
import androidx.room.Room
import com.nomadicDev.pokemonGo.data.data_source.local.db.AppDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppDbDataSource(app: Application): AppDbDataSource {
        return Room
            .databaseBuilder(app, AppDbDataSource::class.java, AppDbDataSource.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDbDataSource: AppDbDataSource) = appDbDataSource.pokemonDao()
}