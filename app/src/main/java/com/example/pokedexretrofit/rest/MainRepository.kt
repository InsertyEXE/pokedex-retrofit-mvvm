package com.example.pokedexretrofit.rest

class MainRepository(private val repository: PokemonDexService) {

    fun buscarPokemon(nome: String) = repository.buscarPokemon(nome)

    fun listarPokemons(limite: Int = 151) = repository.listarPokemon(limite)
}