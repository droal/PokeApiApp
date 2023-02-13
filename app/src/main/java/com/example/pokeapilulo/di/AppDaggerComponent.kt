package com.example.pokeapilulo.di

import android.content.Context
import com.example.pokeapilulo.app.MainActivity
import com.example.pokeapilulo.app.detail.PokemonDetailFragment
import com.example.pokeapilulo.app.pokemon_list.PokemonListFragment
import com.example.pokeapilulo.di.app.ViewModelDaggerModule
import com.example.pokeapilulo.di.data.ApiDaggerModule
import com.example.pokeapilulo.di.data.DataSourceDaggerModule
import com.example.pokeapilulo.di.data.RepositoryDaggerModule
import com.example.pokeapilulo.di.data.RetrofitDaggerModule
import com.example.pokeapilulo.di.domain.InteractorDaggerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiDaggerModule::class,
        DataSourceDaggerModule::class,
        RepositoryDaggerModule::class,
        RetrofitDaggerModule::class,
        InteractorDaggerModule::class,
        ViewModelDaggerModule::class
    ]
)
interface AppDaggerComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppDaggerComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: PokemonListFragment)
    fun inject(fragment: PokemonDetailFragment)
}