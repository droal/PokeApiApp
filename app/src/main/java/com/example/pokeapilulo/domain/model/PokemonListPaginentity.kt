package com.example.pokeapilulo.domain.model

data class PokemonListPaginentity(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<PokemonEntity> = emptyList()
)
