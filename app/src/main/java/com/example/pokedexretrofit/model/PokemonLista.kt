package com.example.pokedexretrofit.model

data class PokemonLista(
    val results: List<PokemonQuery>
)

data class PokemonQuery(
    val name: String,
    val url: String
)
