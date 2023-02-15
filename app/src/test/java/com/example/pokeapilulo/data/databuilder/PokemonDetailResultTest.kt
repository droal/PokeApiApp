package com.example.pokeapilulo.data.databuilder

import com.example.pokeapilulo.data.model.Ability
import com.example.pokeapilulo.data.model.Move
import com.example.pokeapilulo.data.model.PokemonDetailResult

data class PokemonDetailResultTest(
    private val base_experience: Int = 0,
    private val abilities: List<Ability> = emptyList(),
    private val moves: List<Move> = emptyList()
){
    fun build() = PokemonDetailResult(base_experience, abilities, moves)
}
