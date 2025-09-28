# Pokédex App

Una aplicación de Pokédex desarrollada en Android con Kotlin y Jetpack Compose que permite explorar información detallada de los Pokémon utilizando la PokeAPI.

## Descripción del Proyecto

Esta aplicación móvil implementa una Pokédex completa que permite a los usuarios navegar por una lista de Pokémon y ver información detallada de cada uno. La app utiliza arquitectura MVVM (Model-View-ViewModel) con las mejores prácticas de desarrollo Android moderno.

## Características Principales

- ✨ **Lista completa de Pokémon** con imágenes oficiales
- 🔍 **Pantalla de búsqueda** para encontrar Pokémon específicos  
- 📊 **Estadísticas detalladas** de cada Pokémon
- 🖼️ **Galería de imágenes** con sprites oficiales
- 🎨 **Interfaz moderna** desarrollada con Jetpack Compose
- 🌐 **Datos en tiempo real** desde la PokeAPI
- 📱 **Navegación fluida** entre pantallas

## Tecnologías Utilizadas

### Frontend
- **Kotlin** - Lenguaje de programación principal
- **Jetpack Compose** - Framework de UI declarativo
- **Material Design 3** - Sistema de diseño

### Arquitectura y Patrones
- **MVVM (Model-View-ViewModel)** - Patrón de arquitectura
- **StateFlow** - Manejo de estado reactivo
- **Navigation Component** - Navegación entre pantallas

### Networking y Datos
- **Retrofit** - Cliente HTTP para consumir APIs
- **Gson** - Serialización/deserialización JSON
- **Coil** - Carga de imágenes asíncrona
- **PokeAPI** - API REST para datos de Pokémon

### Herramientas de Desarrollo
- **Coroutines** - Programación asíncrona
- **ViewModel** - Manejo del ciclo de vida
- **LiveData/StateFlow** - Observación de datos

## Screenshots

### Pantalla Principal
La pantalla principal muestra una lista de todos los Pokémon disponibles con sus imágenes oficiales y nombres. Cada elemento de la lista es interactivo y permite navegar a los detalles del Pokémon seleccionado.

![Pantalla Principal](https://iili.io/K0o7Jup.png)

*Vista principal con lista de Pokémon, mostrando sprites oficiales y nombres*

### Pantalla de Búsqueda
Funcionalidad de búsqueda que permite encontrar Pokémon específicos de manera rápida y eficiente.

![Pantalla de Búsqueda](https://iili.io/K0oYFOQ.png)

*Interfaz de búsqueda para localizar Pokémon específicos*

### Estadísticas del Pokémon
Vista detallada que muestra todas las estadísticas base del Pokémon seleccionado, incluyendo HP, Ataque, Defensa, y más.

![Estadísticas](https://iili.io/K0oYhI2.png)

*Pantalla de estadísticas mostrando los stats base del Pokémon*

### Galería de Imágenes
Sección dedicada a mostrar diferentes sprites y variantes visuales del Pokémon, incluyendo versiones normales y shiny.

![Imágenes del Pokémon](https://iili.io/K0oa2EX.png)

*Galería de sprites oficiales del Pokémon seleccionado*

## Estructura del Proyecto

```
uvg.edu.lab6/
├── data/
│   ├── model/           # Modelos de datos (Pokemon, PokemonSummary, etc.)
│   ├── network/         # Servicios de red (Retrofit, API)
│   └── repository/      # Repositorios para manejo de datos
├── ui/
│   ├── main/           # Pantalla principal y lista de Pokémon
│   ├── detail/         # Pantallas de detalle del Pokémon
│   └── theme/          # Configuración de tema y colores
└── MainActivity.kt      # Actividad principal y navegación
```

## Funcionalidades Implementadas

### 1. Lista de Pokémon (MainScreen/MainFragment)
- Carga de lista completa de Pokémon desde PokeAPI
- Visualización con imágenes y nombres
- Navegación hacia detalles al tocar un elemento
- Manejo de estados de carga y error

### 2. Detalles del Pokémon (DetailScreen)
- Información básica (altura, peso, tipos)
- Estadísticas base completas
- Galería de sprites (normal, shiny, frontal, trasero)
- Navegación de regreso a la lista

### 3. Funcionalidad de Búsqueda
- Búsqueda en tiempo real
- Filtrado por nombre de Pokémon
- Resultados instantáneos

### 4. Manejo de Estados
- Estados de carga con indicadores visuales
- Manejo de errores de red
- Funcionalidad de reintentar en caso de error

## Patrones de Diseño Utilizados

### Repository Pattern
```kotlin
class PokemonRepository {
    suspend fun getPokemonList(): PokemonListResponse
    suspend fun getPokemonDetail(id: Int): Pokemon
}
```

### MVVM con StateFlow
```kotlin
class MainViewModel : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<PokemonSummary>>(emptyList())
    val pokemonList: StateFlow<List<PokemonSummary>> = _pokemonList.asStateFlow()
}
```

### Composable Functions
```kotlin
@Composable
fun PokemonList(pokemonList: List<PokemonSummary>, onClick: (PokemonSummary) -> Unit)
```

## API Utilizada

**PokeAPI** (https://pokeapi.co/api/v2/)
- `GET /pokemon` - Lista de Pokémon
- `GET /pokemon/{id}` - Detalles específicos del Pokémon

## Instalación y Configuración

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias con Gradle
4. Ejecuta la aplicación en un dispositivo o emulador Android

## Requisitos del Sistema

- **Android API Level**: 24+ (Android 7.0)
- **Kotlin**: 1.8+
- **Android Studio**: Arctic Fox o superior
- **Conexión a Internet**: Requerida para cargar datos de la API

## Autor

Desarrollado como parte del Lab 6 - Universidad del Valle de Guatemala

---

*Esta aplicación fue desarrollada con fines educativos utilizando las mejores prácticas de desarrollo Android moderno.*