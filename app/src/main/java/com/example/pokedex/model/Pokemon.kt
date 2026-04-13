package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val nome: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("sprites")
    val sprites: Sprites
)


