package com.github.kuya32.mobilepokedex.data.remote

import com.github.mobilepokedex.data.remote.responses.Pokemon
import com.github.mobilepokedex.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    // Retrieves list of pokemon from the API based on the limit and offset
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    // Retrieves a particular pokemon's information from the API based on their name
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon
}