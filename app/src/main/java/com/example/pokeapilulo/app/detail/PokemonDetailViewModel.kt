package com.example.pokeapilulo.app.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.LoadingDetail
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.ErrorDetail
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail.SuccesDetail
import com.example.pokeapilulo.app.model.mapToModelView
import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import com.example.pokeapilulo.util.ResponseStatus
import com.example.pokeapilulo.util.SingleMutableLiveData
import com.example.pokeapilulo.util.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(
    private val pokeAppInteractor: PokeAppInteractor
): ViewModel() {

    private val _requestState = SingleMutableLiveData<StatesRequestPokemonDetail>()
    val requestState = _requestState.asLiveData()


    fun getPokemonByName(name: String) = viewModelScope.launch {
        _requestState.value = LoadingDetail(true)

        when(val pokemon = pokeAppInteractor.getPokemonByName(name)){
            is ResponseStatus.Succes -> {
                _requestState.value = LoadingDetail(false)
                _requestState.value = SuccesDetail(pokemon.data.mapToModelView())
            }
            is ResponseStatus.Error -> {
                _requestState.value = LoadingDetail(false)
                _requestState.value = ErrorDetail
            }
        }
    }
}