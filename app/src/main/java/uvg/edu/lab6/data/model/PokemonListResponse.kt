package uvg.edu.lab6.data.model

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonSummary>
)

data class PokemonSummary(
    val name: String,
    val url: String
)