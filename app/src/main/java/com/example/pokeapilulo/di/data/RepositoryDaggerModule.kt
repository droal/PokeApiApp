package com.example.pokeapilulo.di.data

import com.example.pokeapilulo.data.repository.PokeAppRepositoryImp
import com.example.pokeapilulo.domain.repository.PokeAppRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryDaggerModule {

    @Singleton
    @Binds
    abstract fun providePokeAppRepository(
        pokeAppRepositoryImp: PokeAppRepositoryImp
    ): PokeAppRepository
}