package com.example.pokeapilulo.app.model

import com.example.pokeapilulo.domain.model.PokemonDetailEntity

data class PokemonDetailModelView(
    val experience: Int,
    val abilities: List<String>,
    val moves: List<String>,
)

fun PokemonDetailEntity.mapToModelView() = PokemonDetailModelView(
    experience,
    abilities,
    moves
)