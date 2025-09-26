package uvg.edu.lab6.ui.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import uvg.edu.lab6.data.model.PokemonSummary
import uvg.edu.lab6.ui.theme.PokedexTheme

@Composable
fun MainFragment(onPokemonClick: (Int) -> Unit, viewModel: MainViewModel = viewModel()) {
    val pokemonList: List<PokemonSummary> by viewModel.pokemonList.collectAsState(initial = emptyList())
    val isLoading: Boolean by viewModel.isLoading.collectAsState(initial = true)
    val error: String? by viewModel.error.collectAsState(initial = null)

    PokedexTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pokédex",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                when {
                    isLoading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    error != null -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error: $error",
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { viewModel.retry() }) {
                                Text("Retry")
                            }
                        }
                    }
                    pokemonList.isEmpty() -> {
                        Text("No Pokemon found")
                    }
                    else -> {
                        LazyColumn {
                            items(pokemonList) { pokemon ->
                                PokemonListItem(pokemon = pokemon, onClick = onPokemonClick)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: PokemonSummary, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                val urlParts = pokemon.url.split("/").filter { it.isNotEmpty() }
                val pokemonId = urlParts.lastOrNull()?.toIntOrNull() ?: 0
                if (pokemonId > 0) {
                    Log.d("MainFragment", "Navigating to Pokémon ID: $pokemonId")
                    onClick(pokemonId)
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val urlParts = pokemon.url.split("/").filter { it.isNotEmpty() }
            val pokemonId = urlParts.lastOrNull()?.toIntOrNull() ?: 0

            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png",
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase() else char.toString()
                },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}