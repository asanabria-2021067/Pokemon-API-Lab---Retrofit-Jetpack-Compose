package uvg.edu.lab6.data.model

data class PokemonResponse(
    val results: List<PokemonItem>
)

data class PokemonItem(
    val name: String,
    val url: String
) {
    fun getId(): String {
        return url.split("/").dropLast(1).last()
    }

    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getId()}.png"
    }
}
