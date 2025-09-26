package uvg.edu.lab6.data.model

import com.google.gson.annotations.SerializedName

// Modelo completo para los detalles de un Pokemon
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites,
    val types: List<PokemonTypeSlot>,
    val stats: List<PokemonStat>
)

// Sprites del Pokemon (imágenes)
data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_shiny")
    val backShiny: String?
)

// Slot de tipo del Pokemon
data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

// Información del tipo
data class PokemonType(
    val name: String,
    val url: String
)

// Estadística del Pokemon
data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: PokemonStatInfo
)

// Información de la estadística
data class PokemonStatInfo(
    val name: String,
    val url: String
)