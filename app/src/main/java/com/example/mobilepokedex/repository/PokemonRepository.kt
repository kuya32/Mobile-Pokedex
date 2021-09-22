package com.example.mobilepokedex.repository

import com.example.mobilepokedex.data.remote.responses.Pokemon
import com.example.mobilepokedex.data.remote.responses.PokemonList
import com.example.mobilepokedex.utils.Resource

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>?

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>?
}