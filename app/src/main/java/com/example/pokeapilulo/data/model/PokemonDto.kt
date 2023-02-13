package com.example.pokeapilulo.data.model

import com.example.pokeapilulo.domain.model.PokemonEntity

data class PokemonDto(
    val name: String,
    val url: String
)

fun List<PokemonDto>.mapToDomain() = map {
    PokemonEntity(
        name = it.name,
        url = it.url
    )
}
