package ar.edu.unicen.movieapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.movieapp.ddl.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    //LiveData: de afuera reciben información cada vez que cambiamos el contador. De afuera la observan

   private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()


    private  val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()  //cambia a Flow de solo lectura

    init{
        viewModelScope.launch {
            _loading.value = true
            val counterValue: Int = movieRepository.getCounter()
            _counter.value = counterValue
            _loading.value = false
        }
    }


   fun increment() {
       viewModelScope.launch {
           _loading.value = true

           val newCounter: Int = counter.value + 1
           val result: Boolean = movieRepository.setCounter(newCounter)
           if (result)
               _counter.value = newCounter
           _loading.value = false
       }

   }

    fun reset(){
        viewModelScope.launch {
            _loading.value = true

            val newCounter: Int = counter.value + 1
            val result: Boolean = movieRepository.reset()
            if (result)
                _counter.value = 0
            _loading.value = false
        }

    }
}
