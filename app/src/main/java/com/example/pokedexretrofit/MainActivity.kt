package com.example.pokedexretrofit

import android.annotation.SuppressLint
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
    val retrofit = PokemonRetrofitConfig.retrofitConfig()
    val service = retrofit.create(PokemonDexService::class.java)
    private var pokemons: ArrayList<Pokemon> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callbackLista = service.listarPokemon(151)


        callbackLista.enqueue(object : Callback<PokemonLista> {

            override fun onResponse(call: Call<PokemonLista>, response: Response<PokemonLista>) {
                if (response.isSuccessful) {
                    val pokemonLista: PokemonLista? = response.body()

                    pokemonLista.let {
                        for (pokemonQuery in it!!.results) {

                            val url =
                                pokemonQuery.url.replace("https://pokeapi.co/api/v2/pokemon/", "")

                            val callbackPokemon = service.buscarPokemon(url)

                            callbackPokemon.enqueue(object : Callback<Pokemon> {
                                override fun onResponse(
                                    call: Call<Pokemon>,
                                    response: Response<Pokemon>
                                ) {
                                    val pokemon: Pokemon = response.body()!!
                                    pokemons.add(pokemon)

                                    carregarRecyclerview(pokemons)
                                    binding.rvDex.adapter?.notifyDataSetChanged()


                                }

                                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                    //dotô
                                }

                            })

                        }
                    }

                }


            }

            override fun onFailure(call: Call<PokemonLista>, t: Throwable) {
                Log.i("ruim", "ruim")
            }


        })

    }

    @SuppressLint("NotifyDataSetChanged")
    fun carregarRecyclerview(pokemons: List<Pokemon>) {

        binding.rvDex.layoutManager = GridLayoutManager(this, 2)
        binding.rvDex.adapter = PokemonAdapter(pokemons)


    }
}