package com.example.pokeapilulo.di.domain

import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import com.example.pokeapilulo.domain.repository.PokeAppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorDaggerModule {

    @Singleton
    @Provides
    fun providePokeAppInteractor(
        repository: PokeAppRepository
    ): PokeAppInteractor = PokeAppInteractor(repository)
}