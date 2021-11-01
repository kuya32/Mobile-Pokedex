package com.github.kuya32.mobilepokedex.pokemonList

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.github.mobilepokedex.data.models.PokedexListEntry
import com.github.mobilepokedex.data.respository.PokemonRepositoryImpl
import com.github.mobilepokedex.utils.Constants.PAGE_SIZE
import com.github.mobilepokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repositoryImpl: PokemonRepositoryImpl
): ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonList.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) || it.number.toString() == query.trim()
            }
            if(isSearchStarting) {
                cachedPokemonList = pokemonList.value
                isSearchStarting = false
            }
            pokemonList.value = results
            isSearching.value = true
        }
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repositoryImpl.getPokemonList(PAGE_SIZE, currentPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value = currentPage * PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(
                            entry.name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            url,
                            number.toInt()
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                    loadError.value = "Something happened while loading list of Pokemon!"
                    isLoading.value = false
                }
            }
        }
    }

    suspend fun getDrawable(url: String, context: Context): Drawable {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(url)
            .crossfade(true)
            .build()

        return (loader.execute(request) as SuccessResult).drawable
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}