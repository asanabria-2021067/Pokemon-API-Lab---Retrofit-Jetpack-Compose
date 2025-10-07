package uvg.edu.lab6.data.repository

import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.model.PokemonListResponse
import uvg.edu.lab6.data.remote.RetrofitClient

class MainRepository {
    private val api = RetrofitClient.api

    suspend fun getPokemonList(limit: Int = 150): PokemonListResponse {
        return api.getPokemonList(limit)
    }

    suspend fun getPokemonById(id: Int): Pokemon {
        return api.getPokemonDetail(id.toString())
    }

    suspend fun getPokemonByName(name: String): Pokemon {
        return api.getPokemonDetail(name)
    }
}