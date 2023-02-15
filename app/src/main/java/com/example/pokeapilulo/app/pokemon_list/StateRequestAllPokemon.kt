package com.example.pokeapilulo.app.pokemon_list

import com.example.pokeapilulo.app.model.PokemonListModelView

sealed class StatesRequestAllPokemon{

    data class LoadingAllpokemon(val showLoading: Boolean) : StatesRequestAllPokemon()
    data class SuccesGetAllPokemon(val pokemonList: PokemonListModelView) : StatesRequestAllPokemon()
    object ErrorGetAllPokemon : StatesRequestAllPokemon()
}
