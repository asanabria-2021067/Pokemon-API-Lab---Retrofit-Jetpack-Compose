package uvg.edu.lab6.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.repository.PokemonRepository

class DetailViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadPokemon(pokemonId: Int) {
        Log.d("DetailViewModel", "Loading Pokemon ID: $pokemonId")

        if (pokemonId < 1 || pokemonId > 10000) {
            _error.value = "ID de Pokémon inválido: $pokemonId"
            _isLoading.value = false
            Log.e("DetailViewModel", "Invalid Pokemon ID: $pokemonId")
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                Log.d("DetailViewModel", "Making API call for Pokemon ID: $pokemonId")

                val pokemonDetail = repository.getPokemonById(pokemonId)
                _pokemon.value = pokemonDetail

                Log.d("DetailViewModel", "Successfully loaded Pokemon: ${pokemonDetail.name}")

            } catch (e: Exception) {
                val errorMessage = e.message ?: "Error de red o datos inválidos"
                _error.value = errorMessage
                _pokemon.value = null
                Log.e("DetailViewModel", "Error loading Pokemon: $errorMessage", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}