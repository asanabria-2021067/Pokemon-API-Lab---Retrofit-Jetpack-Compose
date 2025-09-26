package uvg.edu.lab6.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF0000),
    onPrimary = Color.White,
    secondary = Color(0xFF000000),
    onSecondary = Color.White,
    tertiary = Color(0xFF7D5260),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF0000),
    onPrimary = Color.White,
    secondary = Color(0xFF000000),
    onSecondary = Color.White,
    tertiary = Color(0xFF7D5260),
    background = Color.White,
    surface = Color.White
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}