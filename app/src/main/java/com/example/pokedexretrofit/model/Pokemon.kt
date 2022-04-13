package com.example.pokedexretrofit.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>
){

    val numeroFormatado = id.toString().padStart(3, '0')
    val imgThumb: String = "https://www.serebii.net/pokemongo/pokemon/$numeroFormatado.png"
}

data class PokemonType(
    val type: NameTypes
)

data class NameTypes(
    val name: String
)

// https://pokeapi.glitch.me/v1/pokemon/448

