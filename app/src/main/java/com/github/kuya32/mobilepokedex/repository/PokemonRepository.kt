package com.github.kuya32.mobilepokedex.repository

import com.github.kuya32.mobilepokedex.data.remote.responses.Pokemon
import com.github.kuya32.mobilepokedex.data.remote.responses.PokemonList
import com.github.kuya32.mobilepokedex.utils.Resource

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>?

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>?
}