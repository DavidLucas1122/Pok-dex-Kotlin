package com.example.pokedex.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class RetrofitFactory {
    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val retrofitFactory = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    fun getPokemonService (): PokemonService {
        return retrofitFactory.create(PokemonService::class.java)
    }
}