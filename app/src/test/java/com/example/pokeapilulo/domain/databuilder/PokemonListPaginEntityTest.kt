package com.example.pokeapilulo.domain.databuilder

import com.example.pokeapilulo.domain.model.PokemonEntity
import com.example.pokeapilulo.domain.model.PokemonListPaginEntity

data class PokemonListPaginEntityTest(
    private val count: Int = 0,
    private val next: String= "",
    private val previous: String= "",
    private val result: List<PokemonEntity> = emptyList()
){
    fun build() = PokemonListPaginEntity(count, next, previous, result)
}
