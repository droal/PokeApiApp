package com.example.pokeapilulo

import android.app.Application
import com.example.pokeapilulo.di.AppDaggerComponent
import com.example.pokeapilulo.di.DaggerAppDaggerComponent

class PokeAppApplication: Application() {

    val appComponent: AppDaggerComponent by lazy {
        DaggerAppDaggerComponent.factory().create(applicationContext)
    }
}