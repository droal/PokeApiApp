package com.example.pokeapilulo.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class LoadImage {

    companion object {
        fun loadImageWhitGladeToVector(context: Context, pokemonUrl: String, imageView: ImageView) {
            val numberOfPokemon = pokemonUrl.split("/").filter { it != "" }.last().toInt()
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/${numberOfPokemon}.svg"

            GlideToVectorYou
                .init()
                .with(context)
                .load(Uri.parse(imageUrl), imageView)

        }
    }

}