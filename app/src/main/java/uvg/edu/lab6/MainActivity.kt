package uvg.edu.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uvg.edu.lab6.ui.theme.PokedexTheme
import uvg.edu.lab6.ui.main.MainScreen
import uvg.edu.lab6.ui.detail.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexApp()
        }
    }
}

@Composable
fun PokedexApp() {
    PokedexTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {
                    MainScreen(navController = navController)
                }

                composable(
                    route = "detail/{pokemonId}",
                    arguments = listOf(navArgument("pokemonId") {
                        type = NavType.IntType
                        defaultValue = 1
                    })
                ) { backStackEntry ->
                    val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 1
                    DetailScreen(
                        pokemonId = pokemonId,
                        navController = navController
                    )
                }
            }
        }
    }
}