package uvg.edu.lab6.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uvg.edu.lab6.data.model.PokemonSummary
import uvg.edu.lab6.data.repository.MainRepository

class MainViewModel(
    private val repository: MainRepository = MainRepository()
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonSummary>>(emptyList())
    val pokemonList: StateFlow<List<PokemonSummary>> = _pokemonList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val pokemonResponse = repository.getPokemonList()
                _pokemonList.value = pokemonResponse.results

            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
                _pokemonList.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retry() {
        loadPokemonList()
    }
}