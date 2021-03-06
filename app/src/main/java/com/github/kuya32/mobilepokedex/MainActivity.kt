package com.github.kuya32.mobilepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.github.kuya32.mobilepokedex.pokemonDetail.PokemonDetailScreen
import com.github.kuya32.mobilepokedex.pokemonList.PokemonListScreen
import com.github.kuya32.mobilepokedex.ui.theme.MobilePokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilePokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemonListScreen"
                ) {
                    composable("pokemonListScreen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        "pokemonDetailScreen/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }

                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName!!.lowercase(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}