package com.example.pokedexretrofit.view


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexretrofit.databinding.ActivityMainBinding
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonAdapter
import com.example.pokedexretrofit.rest.MainRepository
import com.example.pokedexretrofit.rest.PokemonDexService
import com.example.pokedexretrofit.viewmodel.MainViewModel
import com.example.pokedexretrofit.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pokemons: MutableList<Pokemon> = arrayListOf()
    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(
            this,
            MainViewModelFactory(MainRepository(PokemonDexService.getInstance()))
        )[MainViewModel::class.java]

        binding.rvDex.layoutManager = GridLayoutManager(this, 2)


    }


    override fun onResume() {
        super.onResume()

        if (pokemons.isEmpty())
            viewmodel.listarPokemons()

        viewmodel.pokemon.observe(this, Observer {


            if (!pokemons.contains(it))
                pokemons.add(it)

            pokemons.sortBy { pokemon ->
                pokemon.id
            }

            binding.rvDex.adapter = PokemonAdapter(pokemons)

        })
        
    }
}