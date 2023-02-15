package com.example.pokeapilulo.app.model

import com.example.pokeapilulo.domain.model.PokemonListPaginEntity

data class PokemonListModelView(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<PokemonModelView>
)

fun PokemonListPaginEntity.mapToModelView() = PokemonListModelView(count, next, previous, result.mapToModelView())





