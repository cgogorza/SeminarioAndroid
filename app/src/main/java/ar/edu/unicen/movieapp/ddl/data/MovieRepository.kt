package ar.edu.unicen.movieapp.ddl.data

import android.util.Log
import ar.edu.unicen.movieapp.BuildConfig
import ar.edu.unicen.movieapp.ddl.models.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {

    private val authToken = "Bearer ${BuildConfig.TMDB_API_KEY}"

    suspend fun getPopularMovie(): Movie? {
        return try {
            remoteDataSource.loadGenreMap(authToken)
            remoteDataSource.getPopularMovie(authToken)
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error en getPopularMovie: ${e.localizedMessage}", e)
            null
        }
    }

    // Obtener múltiples páginas de películas
    suspend fun getPopularMovies(quantity: Int = 100, pages: Int = 5): List<Movie> {
        return try {
            remoteDataSource.loadGenreMap(authToken)
            remoteDataSource.getPopularMovies(authToken, pages, quantity)
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error en getPopularMovies: ${e.localizedMessage}", e)
            emptyList()
        }
    }

}
