package ar.edu.unicen.movieapp.ddl.data

import android.util.Log
import ar.edu.unicen.movieapp.ddl.data.dto.GenreResponse
import ar.edu.unicen.movieapp.ddl.data.dto.toMovie
import ar.edu.unicen.movieapp.ddl.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    private var genreMap: Map<Int, String>? = null

    // Carga el mapa de géneros desde la API si aún no está disponible
    suspend fun loadGenreMap(authToken: String) {
        if (genreMap == null) {
            try {
                val response: Response<GenreResponse> = movieApi.getGenres(authToken)
                if (response.isSuccessful) {
                    val genreResponse = response.body()
                    genreMap = genreResponse?.genres?.associate { it.id to it.name } ?: emptyMap()
                    Log.d("MovieRemoteDataSource", "Genre map loaded: $genreMap")
                } else {
                    Log.e("MovieRemoteDataSource", "API error loading genres: ${response.errorBody()?.string()}")
                    genreMap = emptyMap()
                }
            } catch (e: Exception) {
                Log.e("MovieRemoteDataSource", "Exception loading genre map: ${e.localizedMessage}", e)
                genreMap = emptyMap()
            }
        }
    }

    // Obtener la película más popular
    suspend fun getPopularMovie(authToken: String): Movie? {
        return withContext(Dispatchers.IO) {
            loadGenreMap(authToken)
            try {
                val response = movieApi.getPopularMovies(authToken)
                if (response.isSuccessful) {
                    val movieDto = response.body()?.results?.firstOrNull()
                    genreMap?.let { movieDto?.toMovie(it) }
                } else {
                    Log.e("MovieRemoteDataSource", "Error API en getPopularMovie: ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("MovieRemoteDataSource", "Error en getPopularMovie: ${e.localizedMessage}", e)
                null
            }
        }
    }

    // Obtener múltiples páginas de películas populares
    suspend fun getPopularMovies(authToken: String, pages: Int, quantity: Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            val allMovies = mutableListOf<Movie>()
            loadGenreMap(authToken)
            try {
                for (page in 1..pages) {
                    val response = movieApi.getPopularMovies(authToken, page = page)
                    if (response.isSuccessful) {
                        val movies = response.body()?.results?.mapNotNull { movieDto ->
                            genreMap?.let { movieDto.toMovie(it) }
                        }
                        if (movies != null) {
                            allMovies.addAll(movies)
                        }
                        // Si alcanzamos la cantidad deseada, rompemos el bucle
                        if (allMovies.size >= quantity) break
                    } else {
                        Log.e("MovieRemoteDataSource", "Error API en getPopularMovies: ${response.errorBody()?.string()}")
                        break
                    }
                }
                // Asegura devolver solo la cantidad deseada
                allMovies.take(quantity)
            } catch (e: Exception) {
                Log.e("MovieRemoteDataSource", "Error en getPopularMovies: ${e.localizedMessage}", e)
                emptyList()
            }
        }
    }
}
