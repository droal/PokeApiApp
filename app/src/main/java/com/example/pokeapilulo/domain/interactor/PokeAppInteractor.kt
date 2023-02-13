package com.example.pokeapilulo.domain.interactor

import com.example.pokeapilulo.domain.repository.PokeAppRepository
import javax.inject.Inject

class PokeAppInteractor @Inject constructor(
    private val repository: PokeAppRepository
) {

    suspend fun getAllPokemon(offset: Int, limit: Int) = repository.getAllPokemon(offset, limit)

    suspend fun getPokemonByName(name: String) = repository.getPokemonByName(name)
}