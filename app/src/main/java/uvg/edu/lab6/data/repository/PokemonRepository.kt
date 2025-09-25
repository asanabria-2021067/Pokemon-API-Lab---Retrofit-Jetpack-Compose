package uvg.edu.lab6.data.repository

import uvg.edu.lab6.data.model.PokemonDetail
import uvg.edu.lab6.data.model.PokemonResponse
import uvg.edu.lab6.data.network.RetrofitInstance

class PokemonRepository {

    private val api = RetrofitInstance.api

    suspend fun getPokemonList(): PokemonResponse {
        return api.getPokemonList()
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return api.getPokemonDetail(id)
    }
}
