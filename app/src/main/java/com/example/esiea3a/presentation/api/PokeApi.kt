package com.example.esiea3a.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//import retrofit2.http.Query
//@Query("200")

interface PokeApi {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonListResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetail(@Path("id") id:Int): Call<PokemonDetailResponse>
}