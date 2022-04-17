package com.example.pokedexretrofit.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRetrofitConfig {

    companion object {

        fun retrofitConfig(): Retrofit {
            val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit

        }

    }
}

//"https://pokeapi.co/api/v2/"