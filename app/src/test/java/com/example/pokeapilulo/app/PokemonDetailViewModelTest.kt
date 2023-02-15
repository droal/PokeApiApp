package com.example.pokeapilulo.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokeapilulo.TestCoroutineRule
import com.example.pokeapilulo.app.detail.PokemonDetailViewModel
import com.example.pokeapilulo.app.detail.StatesRequestPokemonDetail
import com.example.pokeapilulo.app.model.mapToModelView
import com.example.pokeapilulo.domain.databuilder.PokemonDetailEntityTest
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
class PokemonDetailViewModelTest {

    val name = "dito"
    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var observerRequestState: Observer<StatesRequestPokemonDetail>

    private lateinit var pokeAppInteractor: PokeAppInteractor
    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    @Before
    fun setup(){
        pokeAppInteractor = mockk()
        pokemonDetailViewModel = PokemonDetailViewModel(pokeAppInteractor)
        pokemonDetailViewModel.requestState.observeForever(observerRequestState)
    }

    @Test
    fun get_pokemon_by_name_succes() = testCoroutineRule.runBlockingTest {

        val dataTest = PokemonDetailEntityTest().build()
        val response = ResponseStatus.Succes(dataTest)

        //given
        coEvery { pokeAppInteractor.getPokemonByName(name) }.answers{ response }
        //when
        pokemonDetailViewModel.getPokemonByName(name)
        //then
        argumentCaptor {
            verify(observerRequestState, Mockito.times(3)).onChanged(capture())

            Assert.assertEquals(
                response.data.mapToModelView(),
                (allValues[2] as StatesRequestPokemonDetail.SuccesDetail).pokemon
            )
        }
    }

    @Test
    fun get_pokemon_by_name_error() = testCoroutineRule.runBlockingTest {

        val response = ResponseStatus.Error(null, "")

        //given
        coEvery { pokeAppInteractor.getPokemonByName(name) }.answers{ response }
        //when
        pokemonDetailViewModel.getPokemonByName(name)
        //then
        argumentCaptor {
            verify(observerRequestState, Mockito.times(3)).onChanged(capture())

            Assert.assertTrue(allValues[2]  is StatesRequestPokemonDetail.ErrorDetail)
        }
    }
}