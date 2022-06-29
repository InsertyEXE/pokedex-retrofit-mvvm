package com.example.pokedexretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonLista
import com.example.pokedexretrofit.rest.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(val repository: MainRepository) : ViewModel() {

    val pokemon = MutableLiveData<Pokemon>()
    val erroServerMessage = MutableLiveData<String>()
    val errorPokemonNaoEcontrado = MutableLiveData<String>()

    fun listarPokemons() {

        repository.listarPokemons(151)
            .enqueue(object : Callback<PokemonLista> {
                override fun onResponse(
                    call: Call<PokemonLista>,
                    response: Response<PokemonLista>
                ) {

                    if (!response.isSuccessful)
                        erroServerMessage.postValue("Servidor sem resposta")

                    val pokemons = response.body()?.results

                    pokemons?.forEach {

                        val buscarPoke = repository.buscarPokemon(it.name)

                        buscarPoke.enqueue(object : Callback<Pokemon> {
                            override fun onResponse(
                                call: Call<Pokemon>,
                                response: Response<Pokemon>
                            ) {
                                pokemon.value = response.body()

                            }

                            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                                errorPokemonNaoEcontrado.postValue("Pokemon não encontrado")
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<PokemonLista>, t: Throwable) {
                    erroServerMessage.postValue("Servidor sem resposta")
                }

            })
    }
}