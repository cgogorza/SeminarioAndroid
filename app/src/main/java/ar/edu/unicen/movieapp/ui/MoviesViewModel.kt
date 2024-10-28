package ar.edu.unicen.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.movieapp.ddl.data.MovieRepository
import ar.edu.unicen.movieapp.ddl.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>?>(null)
    val movies = _movies.asStateFlow()

    // Traigo varias páginas de películas
    fun getPopularMovies(quantity: Int, pages: Int = 1) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false

            // Llamo al repositorio
            val movies = movieRepository.getPopularMovies(quantity, pages)

            _movies.value = movies
            _error.value = movies == null
            _loading.value = false
        }
    }
}
