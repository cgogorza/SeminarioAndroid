package ar.edu.unicen.movieapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.movieapp.ddl.data.MovieRepository
import ar.edu.unicen.movieapp.ddl.models.MovieRecommendation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private  val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private  val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private  val _recommendation = MutableStateFlow<MovieRecommendation?>(null)
    val  recommendation= _recommendation.asStateFlow()


    fun getRecommendation(
        participants: Int
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _recommendation.value = null

            val recommendation = movieRepository.getRecommendation(participants)

            _loading.value = false
            _recommendation.value = recommendation
            _error.value = recommendation == null
        }

    }
}
