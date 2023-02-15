package com.example.pokeapilulo.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokeapilulo.PokeAppApplication
import com.example.pokeapilulo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as PokeAppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}