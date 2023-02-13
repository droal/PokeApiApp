package com.example.pokeapilulo.di.data

import com.example.pokeapilulo.data.datasource.PokeAppDataSourceImp
import com.example.pokeapilulo.domain.datasource.PokeAppDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceDaggerModule {

    @Singleton
    @Binds
    abstract fun providePokeAppDataSourceImp(
        pokeAppDataSourceImp: PokeAppDataSourceImp
    ) : PokeAppDataSource
}