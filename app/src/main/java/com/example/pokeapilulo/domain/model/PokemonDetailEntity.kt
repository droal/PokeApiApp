package com.example.pokeapilulo.domain.model


data class PokemonDetailEntity(
    val experience: Int,
    val abilities: List<String>,
    val moves: List<String>
)
