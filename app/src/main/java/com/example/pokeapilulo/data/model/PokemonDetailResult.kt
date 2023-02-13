package com.example.pokeapilulo.data.model

data class PokemonDetailResult(
    val base_experience: Int,
    val abilities: List<Ability>,
    val moves: List<Move>
)
