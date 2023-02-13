package com.example.pokeapilulo.data.api

import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDetailResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<AllPokemonResult>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path(value = "name") name: String): Response<PokemonDetailResult>
}