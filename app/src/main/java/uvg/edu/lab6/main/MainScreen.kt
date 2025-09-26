package uvg.edu.lab6.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val error: String? by viewModel.error.collectAsState(initial = null)

    var searchQuery by remember { mutableStateOf("") }

    val filteredList = pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }

    PokedexTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pokédex", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Search Pokémon") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

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
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        }
                        else -> {
                            PokemonGrid(filteredList) { pokemon ->
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
}

@Composable
fun PokemonGrid(pokemonList: List<PokemonSummary>, onClick: (PokemonSummary) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
            .clickable { onClick(pokemon) }
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            val urlParts = pokemon.url.split("/").filter { it.isNotEmpty() }
            val pokemonId = urlParts.lastOrNull()?.toIntOrNull() ?: 0

            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png",
                contentDescription = pokemon.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}