package com.example.mobilepokedex.pokemonDetail

import androidx.lifecycle.ViewModel
import com.example.mobilepokedex.data.remote.responses.Pokemon
import com.example.mobilepokedex.data.respository.PokemonRepositoryImpl
import com.example.mobilepokedex.repository.PokemonRepository
import com.example.mobilepokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
): ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}