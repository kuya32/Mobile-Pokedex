package com.example.mobilepokedex.data.respository

import com.example.mobilepokedex.data.remote.PokeApi
import com.example.mobilepokedex.data.remote.responses.Pokemon
import com.example.mobilepokedex.data.remote.responses.PokemonList
import com.example.mobilepokedex.repository.PokemonRepository
import com.example.mobilepokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
): PokemonRepository  {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}