package com.example.pokeapilulo.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokeapilulo.TestCoroutineRule
import com.example.pokeapilulo.app.model.mapToModelView
import com.example.pokeapilulo.app.pokemon_list.PokemonListViewModel
import com.example.pokeapilulo.app.pokemon_list.StatesRequestAllPokemon
import com.example.pokeapilulo.domain.databuilder.PokemonListPaginEntityTest
import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import com.example.pokeapilulo.util.ResponseStatus
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokemonListViewModelTest {

    val offset = 0
    val limit = 20

    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var observerRequestState: Observer<StatesRequestAllPokemon>

    private lateinit var pokeAppInteractor: PokeAppInteractor
    private lateinit var pokemonListViewModel: PokemonListViewModel

    @Before
    fun setup(){
        pokeAppInteractor = mockk()
        pokemonListViewModel = PokemonListViewModel(pokeAppInteractor)
        pokemonListViewModel.requestState.observeForever(observerRequestState)
    }

    @Test
    fun get_all_pokemon_succes() = testCoroutineRule.runBlockingTest {

        val dataTest = PokemonListPaginEntityTest().build()
        val response = ResponseStatus.Succes(dataTest)

        //given
        coEvery { pokeAppInteractor.getAllPokemon(offset, limit) }.answers{ response }
        //when
        pokemonListViewModel.getAllPokemon(offset, limit)
        //then
        argumentCaptor {
            verify(observerRequestState, Mockito.times(3)).onChanged(capture())

            Assert.assertEquals(
                response.data.mapToModelView().result,
                (allValues[2] as StatesRequestAllPokemon.SuccesGetAllPokemon).pokemonList.result
            )
        }
    }

    @Test
    fun get_all_pokemon_error() = testCoroutineRule.runBlockingTest {

        val response = ResponseStatus.Error(null, "")

        //given
        coEvery { pokeAppInteractor.getAllPokemon(offset, limit) }.answers{ response }
        //when
        pokemonListViewModel.getAllPokemon(offset, limit)
        //then
        argumentCaptor {
            verify(observerRequestState, Mockito.times(3)).onChanged(capture())

            Assert.assertTrue(allValues[2]  is StatesRequestAllPokemon.ErrorGetAllPokemon)
        }
    }
}