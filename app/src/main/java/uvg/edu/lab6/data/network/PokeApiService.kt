package uvg.edu.lab6.data.network

import uvg.edu.lab6.data.model.Pokemon
import uvg.edu.lab6.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 100): PokemonListResponse

    @GET("pokemon/{identifier}")
    suspend fun getPokemonDetail(@Path("identifier") identifier: String): Pokemon
}