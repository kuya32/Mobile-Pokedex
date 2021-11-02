package com.github.kuya32.mobilepokedex.pokemonDetail

import androidx.lifecycle.ViewModel
import com.github.kuya32.mobilepokedex.data.remote.responses.Pokemon
import com.github.kuya32.mobilepokedex.data.respository.PokemonRepositoryImpl
import com.github.kuya32.mobilepokedex.utils.Resource
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