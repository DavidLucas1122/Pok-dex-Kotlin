package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val nome: String = "",
    val id: Int = 0,
    val imagem: String = "",
    val tipos: String = "",
)