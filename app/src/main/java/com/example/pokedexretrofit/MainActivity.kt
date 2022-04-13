package com.example.pokedexretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexretrofit.databinding.ActivityMainBinding
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonAdapter
import com.example.pokedexretrofit.model.PokemonLista
import com.example.pokedexretrofit.rest.PokemonDexService
import com.example.pokedexretrofit.rest.PokemonRetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = PokemonRetrofitConfig.retrofitConfig()
        val service = retrofit.create(PokemonDexService::class.java)
        val callback = service.listarPokemon(6)

        callback.enqueue(object: Callback<PokemonLista> {

            override fun onResponse(call: Call<PokemonLista>, response: Response<PokemonLista>) {
                if (response.isSuccessful)
                    Log.i("servidor", response.body().toString())

            }

            override fun onFailure(call: Call<PokemonLista>, t: Throwable) {
                Log.i("ruim", "ruim")
            }


        })

    }

    fun carregarRecyclerview(pokemons: List<Pokemon>){
        binding.rvDex.layoutManager = GridLayoutManager(this, 3)
        binding.rvDex.adapter = PokemonAdapter(pokemons)
    }
}