package com.example.pokeapilulo.data.datasource

import com.example.pokeapilulo.data.api.PokeApi
import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDetailResult
import com.example.pokeapilulo.domain.datasource.PokeAppDataSource
import com.example.pokeapilulo.util.ResponseAnalyzer
import com.example.pokeapilulo.util.ResponseWrapper
import javax.inject.Inject

class PokeAppDataSourceImp @Inject constructor(
    private val pokeApi: PokeApi
) : PokeAppDataSource, ResponseAnalyzer() {


    override suspend fun getAllPokemon(offset: Int, limit: Int): ResponseWrapper<AllPokemonResult> = getResponseResult {
        pokeApi.getAllPokemon(offset, limit)
    }

    override suspend fun getPokemonByName(name: String): ResponseWrapper<PokemonDetailResult> = getResponseResult {
        pokeApi.getPokemonByName(name)
    }
}