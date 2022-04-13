package com.example.pokedexretrofit.rest


import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonLista
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonDexService {

    @GET("pokemon/{pokemon}")
    fun buscarPokemon(@Path("pokemon") pokemon: Int): Call<Pokemon>

    @GET("pokemon")
    fun listarPokemon(@Query("limit") limit: Int): Call<PokemonLista>
}