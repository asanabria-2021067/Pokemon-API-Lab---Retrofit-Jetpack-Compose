package uvg.edu.lab6.ui

object Screen {
    const val MAIN = "main"
    const val DETAIL = "detail/{pokemonId}"

    fun createRoute(route: String) = route
    fun detailRoute(pokemonId: Int) = "detail/$pokemonId"
}