package com.example.pokeapilulo.domain.repository

import com.example.pokeapilulo.domain.model.PokemonDetailEntity
import com.example.pokeapilulo.domain.model.PokemonListPaginEntity
import com.example.pokeapilulo.util.ResponseStatus

interface PokeAppRepository {

    suspend fun getAllPokemon(offset: Int, limit: Int): ResponseStatus<PokemonListPaginEntity>

    suspend fun getPokemonByName(name: String): ResponseStatus<PokemonDetailEntity>
}