package uvg.edu.lab6.data.repository

import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.model.PokemonSummary
import uvg.edu.lab6.data.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PokemonRepository {
    fun getPokemonList(): Flow<Result<List<PokemonSummary>>> = flow {
        try {
            val response = RetrofitInstance.api.getPokemonList()
            emit(Result.success(response.results))
        } catch (e: IOException) {
            emit(Result.failure(e))
        }
    }

    fun getPokemonDetail(name: String): Flow<Result<Pokemon>> = flow {
        try {
            val pokemon = RetrofitInstance.api.getPokemonDetail(name)
            emit(Result.success(pokemon))
        } catch (e: IOException) {
            emit(Result.failure(e))
        }
    }
}