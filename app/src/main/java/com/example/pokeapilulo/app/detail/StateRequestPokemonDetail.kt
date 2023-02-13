package com.example.pokeapilulo.app.detail

import com.example.pokeapilulo.app.model.PokemonDetailModelView

sealed class StatesRequestPokemonDetail{

    data class LoadingDetail(val showLoading: Boolean) : StatesRequestPokemonDetail()
    data class SuccesDetail(val pokemon: PokemonDetailModelView) : StatesRequestPokemonDetail()
    object ErrorDetail : StatesRequestPokemonDetail()
}
