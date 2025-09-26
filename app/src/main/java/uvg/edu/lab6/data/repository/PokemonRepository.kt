package uvg.edu.lab6.data.repository

import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.model.PokemonListResponse
import uvg.edu.lab6.data.network.RetrofitInstance

class PokemonRepository {
    private val api = RetrofitInstance.api

    suspend fun getPokemonList(limit: Int = 151): PokemonListResponse {
        return api.getPokemonList(limit)
    }

    suspend fun getPokemonDetail(name: String): Pokemon {
        return api.getPokemonDetail(name)
    }

    suspend fun getPokemonById(id: Int): Pokemon {
        return api.getPokemonDetail(id.toString())
    }
}