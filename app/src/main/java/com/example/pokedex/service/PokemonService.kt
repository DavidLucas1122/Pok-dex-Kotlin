package com.example.pokedex.service

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonDetalhes
import com.example.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{pokemon}/")
    fun getPokemonByIdName(@Path("pokemon") pokemon: String): Call<PokemonDetalhes>


    @GET("pokemon/")
    fun getPokemonList(): Call<PokemonResponse>
}