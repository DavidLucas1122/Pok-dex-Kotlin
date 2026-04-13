package com.example.pokedex.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonResponse
import com.example.pokedex.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var listaPokemon by remember {
        mutableStateOf(arrayOf<Pokemon>())
    }

    //Unit garante apenas 1 chamada
    LaunchedEffect(Unit){
        val call = RetrofitFactory().getPokemonService().getPokemonList()

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                listaPokemon = response.body()!!.results
            }

            override fun onFailure(
                call: Call<PokemonResponse>,
                t: Throwable
            ) {
                Log.i("TESTE", "${ t.message }")
            }
        })
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.red))
                .padding(20.dp)
                .height(60.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.red)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color.White,
                    painter = painterResource(R.drawable.game),
                    contentDescription = "Pokébola",
                )


                Text(
                    text = "Pokédex",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    shape = RoundedCornerShape(12.dp),
                    placeholder = { Text(text = "Nome ou ID", color = Color.Black, textAlign = TextAlign.Center) },
                    modifier = Modifier
                        .height(55.dp)
                        .weight(2f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    trailingIcon = {
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.Search, contentDescription = "")
                        }
                    }
                )
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(listaPokemon) {
                    CardPokemon(it)
                }
            }
        }

    }
}

@Composable
fun CardPokemon(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = pokemon.sprites.imagemUrl,
                contentDescription = pokemon.nome,
            )

            Text(
                text = pokemon.nome.replaceFirstChar { it.uppercase() }
            )
        }
    }
}