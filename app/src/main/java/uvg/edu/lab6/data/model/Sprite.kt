package uvg.edu.lab6.data.model

data class Sprites(
    val frontDefault: String?,
    val backDefault: String?,
    val frontShiny: String?,
    val backShiny: String?
) {
    val imageUrls: List<String?>
        get() = listOf(frontDefault, backDefault, frontShiny, backShiny)
}