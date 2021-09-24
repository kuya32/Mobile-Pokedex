package com.example.mobilepokedex.pokemonDetail

import androidx.lifecycle.ViewModel
import com.example.mobilepokedex.data.remote.responses.Pokemon
import com.example.mobilepokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}