package com.example.esiea3a.presentation.api

import jdk.nashorn.internal.runtime.PropertyDescriptor.GET
import retrofit2.Call
import retrofit2.http.GET


interface PokeApi {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonResponse>
}