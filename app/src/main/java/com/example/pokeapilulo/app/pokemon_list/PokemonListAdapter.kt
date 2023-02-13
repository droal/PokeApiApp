package com.example.pokeapilulo.app.pokemon_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapilulo.app.model.PokemonModelView
import com.example.pokeapilulo.databinding.ItemPokemonListBinding
import com.example.pokeapilulo.util.LoadImage

class PokemonListAdapter(
    val listener: (PokemonModelView) -> Unit
) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>(){

    private var pokemonList = mutableListOf<PokemonModelView>()

    fun updateLis(newData: List<PokemonModelView>, oldCount: Int, listSize: Int){
        pokemonList = newData.toMutableList()
        notifyItemRangeInserted(oldCount, listSize)
    }

    fun setPokemonList(newData: List<PokemonModelView>){
        pokemonList = newData.toMutableList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(
        private val bindingItem: ItemPokemonListBinding,
        private val context: Context
    ): RecyclerView.ViewHolder(bindingItem.root){
        fun bindItem(item: PokemonModelView){

            LoadImage.loadImageWhitGladeToVector(context, item.url, bindingItem.pokemonListItemImage)

            bindingItem.pokemonListItemTitle.text = item.name

            bindingItem.root.setOnClickListener {
                listener(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingItem = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(bindingItem, parent.context)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonList[position])
    }
}