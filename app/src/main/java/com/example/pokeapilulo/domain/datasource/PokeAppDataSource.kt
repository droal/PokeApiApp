package com.example.pokeapilulo.domain.datasource

import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDetailResult
import com.example.pokeapilulo.util.ResponseWrapper

interface PokeAppDataSource {

    suspend fun getAllPokemon(offset: Int, limit: Int): ResponseWrapper<AllPokemonResult>

    suspend fun getPokemonByName(name: String): ResponseWrapper<PokemonDetailResult>
}