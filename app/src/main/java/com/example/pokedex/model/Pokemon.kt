package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val nome: String,

    @SerializedName("url")
    val url: String
)