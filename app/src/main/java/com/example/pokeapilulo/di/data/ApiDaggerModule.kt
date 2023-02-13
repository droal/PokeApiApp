package com.example.pokeapilulo.di.data

import com.example.pokeapilulo.data.api.PokeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiDaggerModule {

    @Singleton
    @Provides
    fun providePokeApi(
        retrofit: Retrofit
    ): PokeApi = retrofit.create(PokeApi::class.java)
}