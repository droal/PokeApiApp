package com.example.pokeapilulo.data.databuilder

import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDto

data class AllPokemonResultTest(
    private val count: Int? = 0,
    private val next: String? = "",
    private val previous: String? = "",
    private val results: List<PokemonDto>? = emptyList()
){
    fun build() = AllPokemonResult(count, next, previous, results)

}
