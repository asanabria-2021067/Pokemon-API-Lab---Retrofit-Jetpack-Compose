package uvg.edu.lab6.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>
)

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)

data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String
)

data class AbilitySlot(
    val ability: Ability,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

data class Ability(
    val name: String,
    val url: String
)
