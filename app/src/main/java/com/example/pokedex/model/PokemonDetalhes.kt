package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonDetalhes (
    @SerializedName("id")
    val id: String,

    @SerializedName("sprites")
    val sprites: Sprites,


)