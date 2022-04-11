package com.example.pokedexretrofit.model

data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<String>
){

    val numeroFormatado = number.toString().padStart(3, '0')
    val imgThumb: String = "https://www.serebii.net/pokemongo/pokemon/$numeroFormatado.png"
}


// https://pokeapi.glitch.me/v1/pokemon/448

