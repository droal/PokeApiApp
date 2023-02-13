package com.example.pokeapilulo.di.app

import androidx.lifecycle.ViewModel
import com.example.pokeapilulo.app.pokemon_list.PokemonListViewModel
import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelDaggerModule {

    @Singleton
    @Provides
    fun providePokemonListViewModel(
        interactor: PokeAppInteractor
    ): ViewModel = PokemonListViewModel(interactor)
}