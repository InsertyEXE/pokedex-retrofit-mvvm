package com.example.pokedexretrofit.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    var numeroFormatado: String,
    var imgThumb: String
)

data class PokemonType(
    val type: NameTypes
)

data class NameTypes(
    val name: String
)

// https://pokeapi.glitch.me/v1/pokemon/448

