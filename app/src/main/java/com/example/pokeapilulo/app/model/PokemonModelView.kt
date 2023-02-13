package com.example.pokeapilulo.app.model

import android.os.Parcelable
import com.example.pokeapilulo.domain.model.PokemonEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModelView(
    val name: String,
    val url: String
) : Parcelable

fun List<PokemonEntity>.mapToModelView() = map {
    PokemonModelView(
        name = it.name,
        url = it.url
    )
}