package com.example.pokeapilulo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokeapilulo.TestCoroutineRule
import com.example.pokeapilulo.data.api.PokeApi
import com.example.pokeapilulo.data.databuilder.AllPokemonResultTest
import com.example.pokeapilulo.data.databuilder.PokemonDetailResultTest
import com.example.pokeapilulo.data.datasource.PokeAppDataSourceImp
import com.example.pokeapilulo.data.model.AllPokemonResult
import com.example.pokeapilulo.data.model.PokemonDetailResult
import com.example.pokeapilulo.domain.datasource.PokeAppDataSource
import com.example.pokeapilulo.util.ResponseWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokeAppDataSourceTest {

    val offset = 0
    val limit = 20
    val name = "ditto"

    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var pokeApi: PokeApi

    private lateinit var pokeAppDataSource: PokeAppDataSource

    @Before
    fun setup(){
        pokeApi = mockk()
        pokeAppDataSource = PokeAppDataSourceImp(pokeApi)
    }

    @Test
    fun get_all_pokemon_succes() = testCoroutineRule.runBlockingTest {
        val response = AllPokemonResultTest().build()
        val expected = ResponseWrapper.getResponseSuccess(response)

        //given
        coEvery { pokeApi.getAllPokemon(offset, limit) }.answers{ Response.success(response)}
        //when
        val result = pokeAppDataSource.getAllPokemon(offset, limit)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_all_pokemon_error() = testCoroutineRule.runBlockingTest {

        val errorBody = "".toResponseBody("application/json".toMediaType())
        val errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR
        val responseError:Response<AllPokemonResult> = Response.error(errorCode, errorBody)

        //given
        coEvery { pokeApi.getAllPokemon(offset, limit) }.answers{responseError}
        //when
        val result = pokeAppDataSource.getAllPokemon(offset, limit)
        //then
        Assert.assertTrue(result.responseStatus is ResponseWrapper.ResponseStatus.ERROR)
        Assert.assertEquals(errorCode, (result.responseStatus as ResponseWrapper.ResponseStatus.ERROR).errorCode)
    }

    @Test
    fun get_pokemon_by_name_succes() = testCoroutineRule.runBlockingTest {
        val response = PokemonDetailResultTest().build()
        val expected = ResponseWrapper.getResponseSuccess(response)

        //given
        coEvery { pokeApi.getPokemonByName(name) }.answers{ Response.success(response)}
        //when
        val result = pokeAppDataSource.getPokemonByName(name)
        //then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun get_pokemon_by_name_error() = testCoroutineRule.runBlockingTest {

        val errorBody = "".toResponseBody("application/json".toMediaType())
        val errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR
        val response:Response<PokemonDetailResult> = Response.error(errorCode, errorBody)

        //given
        coEvery { pokeApi.getPokemonByName(name) }.answers{response}
        //when
        val result = pokeAppDataSource.getPokemonByName(name)
        //then
        Assert.assertTrue(result.responseStatus is ResponseWrapper.ResponseStatus.ERROR)
        Assert.assertEquals(errorCode, (result.responseStatus as ResponseWrapper.ResponseStatus.ERROR).errorCode)
    }
}
