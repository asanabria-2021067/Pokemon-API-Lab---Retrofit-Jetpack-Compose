package uvg.edu.lab6

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import uvg.edu.lab6.ui.main.MainFragment
import uvg.edu.lab6.ui.detail.DetailScreen

object Navigation {
    @Composable
    fun MainScreen(navController: NavController) {
        MainFragment(onPokemonClick = { pokemonId ->
            navController.navigate("detail/$pokemonId")
        })
    }

    @Composable
    fun DetailScreen(pokemonId: Int, navController: NavController) {
        DetailScreen(
            pokemonId = pokemonId,
            navController = navController
        )
    }
}