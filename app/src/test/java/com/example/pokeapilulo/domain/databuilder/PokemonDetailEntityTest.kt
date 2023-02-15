package com.example.pokeapilulo.domain.databuilder

import com.example.pokeapilulo.domain.model.PokemonDetailEntity

data class PokemonDetailEntityTest(
    private val experience: Int = 0,
    private val abilities: List<String> = emptyList(),
    private val moves: List<String> = emptyList()
){
    fun build() = PokemonDetailEntity(experience, abilities, moves)
}
