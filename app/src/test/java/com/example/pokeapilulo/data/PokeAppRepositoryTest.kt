package com.example.pokeapilulo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokeapilulo.TestCoroutineRule
import com.example.pokeapilulo.data.databuilder.AllPokemonResultTest
import com.example.pokeapilulo.data.databuilder.PokemonDetailResultTest
import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDetailResult
import com.example.pokeapilulo.domain.databuilder.PokemonListPaginEntityTest
import com.example.pokeapilulo.data.repository.PokeAppRepositoryImp
import com.example.pokeapilulo.domain.databuilder.PokemonDetailEntityTest
import com.example.pokeapilulo.domain.datasource.PokeAppDataSource
import com.example.pokeapilulo.domain.repository.PokeAppRepository
import com.example.pokeapilulo.util.ResponseStatus
import com.example.pokeapilulo.util.ResponseWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokeAppRepositoryTest {

    val offset = 0
    val limit = 20
    val name = "ditto"

    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var pokeAppDataSource: PokeAppDataSource
    private lateinit var pokeAppRepository: PokeAppRepository

    @Before
    fun setup(){
        pokeAppDataSource = mockk()
        pokeAppRepository = PokeAppRepositoryImp(pokeAppDataSource)
    }

    @Test
    fun get_all_pokemon_succes() = testCoroutineRule.runBlockingTest {

        val response = ResponseWrapper.getResponseSuccess(AllPokemonResultTest().build())
        val expected = ResponseStatus.Succes(PokemonListPaginEntityTest().build())

        //given
        coEvery { pokeAppDataSource.getAllPokemon(offset, limit) }.answers{response}
        //when
        val result = pokeAppRepository.getAllPokemon(offset, limit)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_all_pokemon_error() = testCoroutineRule.runBlockingTest {

        val response = ResponseWrapper.getResponseError<AllPokemonResult>(
            Exception(),
            HttpURLConnection.HTTP_INTERNAL_ERROR,
            "",
            null
        )
        val expected = ResponseStatus.Error(null ,"")

        //given
        coEvery { pokeAppDataSource.getAllPokemon(offset, limit) }.answers{response}
        //when
        val result = pokeAppRepository.getAllPokemon(offset, limit)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_pokemon_by_name_succes() = testCoroutineRule.runBlockingTest {

        val response = ResponseWrapper.getResponseSuccess(PokemonDetailResultTest().build())
        val expected = ResponseStatus.Succes(PokemonDetailEntityTest().build())

        //given
        coEvery { pokeAppDataSource.getPokemonByName(name) }.answers{response}
        //when
        val result = pokeAppRepository.getPokemonByName(name)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_pokemon_by_name_error() = testCoroutineRule.runBlockingTest {

        val response = ResponseWrapper.getResponseError<PokemonDetailResult>(
            Exception(),
            HttpURLConnection.HTTP_INTERNAL_ERROR,
            "",
            null
        )
        val expected = ResponseStatus.Error(null ,"")

        //given
        coEvery { pokeAppDataSource.getPokemonByName(name) }.answers{response}
        //when
        val result = pokeAppRepository.getPokemonByName(name)
        //then
        Assert.assertEquals(expected, result)
    }
}