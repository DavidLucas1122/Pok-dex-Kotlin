package com.example.pokedex.service

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("{https://pokeapi.co/api/v2/pokemon}/")
    fun getPokemonByIdName(@Path("pokemon") pokemon: String): Call<Pokemon>


    @GET("https://pokeapi.co/api/v2/pokemon/")
    fun getPokemonList(): Call<PokemonResponse>
}