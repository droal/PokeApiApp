package com.example.pokeapilulo.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokeapilulo.TestCoroutineRule
import com.example.pokeapilulo.domain.databuilder.PokemonDetailEntityTest
import com.example.pokeapilulo.domain.databuilder.PokemonListPaginEntityTest
import com.example.pokeapilulo.domain.interactor.PokeAppInteractor
import com.example.pokeapilulo.domain.repository.PokeAppRepository
import com.example.pokeapilulo.util.ResponseStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokeAppInteractorTest {

    val offset = 0
    val limit = 20
    val name = "ditto"

    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var pokeAppRepository: PokeAppRepository
    private lateinit var pokeAppInteractor: PokeAppInteractor

    @Before
    fun setup(){
        pokeAppRepository = mockk()
        pokeAppInteractor = PokeAppInteractor(pokeAppRepository)
    }

    @Test
    fun get_all_pokemon_succes() = testCoroutineRule.runBlockingTest {

        val expected = ResponseStatus.Succes(PokemonListPaginEntityTest().build())

        //given
        coEvery { pokeAppRepository.getAllPokemon(offset, limit) }.answers{ expected }
        //when
        val result = pokeAppInteractor.getAllPokemon(offset, limit)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_all_pokemon_error() = testCoroutineRule.runBlockingTest {

        val expected = ResponseStatus.Error(null,"")

        //given
        coEvery { pokeAppRepository.getAllPokemon(offset, limit) }.answers{ expected }
        //when
        val result = pokeAppInteractor.getAllPokemon(offset, limit)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_pokemon_by_name_succes() = testCoroutineRule.runBlockingTest {

        val expected = ResponseStatus.Succes(PokemonDetailEntityTest().build())

        //given
        coEvery { pokeAppRepository.getPokemonByName(name) }.answers{ expected }
        //when
        val result = pokeAppInteractor.getPokemonByName(name)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_pokemon_by_name_error() = testCoroutineRule.runBlockingTest {

        val expected = ResponseStatus.Error(null,"")

        //given
        coEvery { pokeAppRepository.getPokemonByName(name) }.answers{ expected }
        //when
        val result = pokeAppInteractor.getPokemonByName(name)
        //then
        Assert.assertEquals(expected, result)
    }

}