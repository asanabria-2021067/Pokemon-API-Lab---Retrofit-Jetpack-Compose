package uvg.edu.lab6.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uvg.edu.lab6.ui.Screen
import uvg.edu.lab6.data.model.PokemonSummary
import uvg.edu.lab6.ui.theme.PokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val pokemonList: List<PokemonSummary> by viewModel.pokemonList.collectAsState(initial = emptyList())
    val isLoading: Boolean by viewModel.isLoading.collectAsState(initial = true)

    PokedexTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pokedex", fontWeight = FontWeight.Bold) }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when {
                    isLoading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        PokemonList(pokemonList) { pokemon ->
                            // Navegamos usando el ID extraído de la URL
                            val urlParts = pokemon.url.split("/").filter { it.isNotEmpty() }
                            val id = urlParts.lastOrNull()?.toIntOrNull() ?: 0
                            navController.navigate(Screen.detailRoute(id))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonList(pokemonList: List<PokemonSummary>, onClick: (PokemonSummary) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonListItem(pokemon, onClick)
        }
    }
}

@Composable
fun PokemonListItem(pokemon: PokemonSummary, onClick: (PokemonSummary) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(pokemon) }
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            // Obtener el ID del Pokemon para construir la URL correcta de la imagen
            val urlParts = pokemon.url.split("/").filter { it.isNotEmpty() }
            val pokemonId = urlParts.lastOrNull()?.toIntOrNull() ?: 0

            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png",
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase() else char.toString()
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}