package com.example.esiea3a.presentation.list

sealed class PokemonModel
    //maitrise l'héritage

data class PokemonSuccess(val pokeList : List<Pokemon>) : PokemonModel()
object PokemonLoader : PokemonModel()
object PokemonError : PokemonModel()