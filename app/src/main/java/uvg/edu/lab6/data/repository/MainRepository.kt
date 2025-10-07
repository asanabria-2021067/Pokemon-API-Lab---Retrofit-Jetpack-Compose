package uvg.edu.lab6.data.repository

import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.model.PokemonListResponse

class MainRepository {
    private val api = `RetrofitClient.kt`.api

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