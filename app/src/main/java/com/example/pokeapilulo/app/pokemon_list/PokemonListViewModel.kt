package com.example.pokeapilulo.app.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapilulo.app.model.mapToModelView
import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import com.example.pokeapilulo.util.ResponseStatus.Succes
import com.example.pokeapilulo.util.ResponseStatus.Error
import com.example.pokeapilulo.util.SingleMutableLiveData
import com.example.pokeapilulo.util.asLiveData
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.SuccesGetAllPokemon
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.ErrorGetAllPokemon
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon.LoadingAllpokemon
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val pokeAppInteractor: PokeAppInteractor
): ViewModel() {


    private val _requestState = SingleMutableLiveData<StatesRequestAllPokemon>()
    val requestState = _requestState.asLiveData()


    fun getAllPokemon(offset: Int, limit: Int) = viewModelScope.launch {
        _requestState.value = LoadingAllpokemon(true)

        when(val pokemonList = pokeAppInteractor.getAllPokemon(offset, limit)){
            is Succes -> {
                _requestState.value = LoadingAllpokemon(false)
                _requestState.value = SuccesGetAllPokemon(pokemonList.data.mapToModelView())
            }
            is Error -> {
                _requestState.value = LoadingAllpokemon(false)
                _requestState.value = ErrorGetAllPokemon
            }
        }
    }


}