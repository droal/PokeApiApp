package com.example.pokeapilulo.data.repository

import com.example.pokeapilulo.data.model.mapToDomain
import com.example.pokeapilulo.domain.datasource.PokeAppDataSource
import com.example.pokeapilulo.domain.model.PokemonDetailEntity
import com.example.pokeapilulo.domain.model.PokemonListPaginEntity
import com.example.pokeapilulo.domain.repository.PokeAppRepository
import com.example.pokeapilulo.util.ResponseStatus
import com.example.pokeapilulo.util.ResponseWrapper.ResponseStatus.SUCCESS
import com.example.pokeapilulo.util.ResponseWrapper.ResponseStatus.ERROR
import javax.inject.Inject

class PokeAppRepositoryImp @Inject constructor(
    private val pokeAppDataSource: PokeAppDataSource
) : PokeAppRepository{

    override suspend fun getAllPokemon(offset: Int, limit: Int): ResponseStatus<PokemonListPaginEntity> {

        val response = pokeAppDataSource.getAllPokemon(offset, limit)
        return when (response.responseStatus) {

            is SUCCESS -> response.responseData.let {
                 ResponseStatus.Succes(
                    PokemonListPaginEntity(
                        count = it?.count ?: 0,
                        next = it?.next ?: "",
                        previous = it?.previous ?: "",
                        result = it?.results?.mapToDomain() ?: listOf()
                    )
                )
            }

            is ERROR -> ResponseStatus.Error(message = "")
        }
    }

    override suspend fun getPokemonByName(name: String): ResponseStatus<PokemonDetailEntity> {
        val response = pokeAppDataSource.getPokemonByName(name)
        return when (response.responseStatus) {

            is SUCCESS -> response.responseData.let {
                ResponseStatus.Succes(
                    PokemonDetailEntity(
                        experience = it?.base_experience ?: 0,
                        abilities = it?.abilities?.map {abilityItem ->
                            abilityItem.ability.name } ?: listOf(),
                        moves = it?.moves?.map {moveItem ->
                            moveItem.move.name }  ?: listOf()
                    )
                )
            }

            is ERROR -> ResponseStatus.Error(message = "")
        }
    }


}

