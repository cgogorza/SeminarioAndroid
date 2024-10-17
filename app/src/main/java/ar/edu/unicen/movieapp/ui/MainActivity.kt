package ar.edu.unicen.movieapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ar.edu.unicen.movieapp.databinding.ActivityMainBinding
import ar.edu.unicen.movieapp.ddl.MovieLocalDataSource
import ar.edu.unicen.movieapp.ddl.MovieRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el botón para incrementar el contador
        binding.incrementButton.setOnClickListener() {
            viewModel.increment()
        }

        binding.resetButton.setOnClickListener(){
            viewModel.reset()
        }

        //usando flow
        viewModel.counter.onEach { valor->
            binding.counterTextView.text = valor.toString()
        }.launchIn(lifecycleScope)


        //Proceso de carga
        viewModel.loading.onEach { loading ->
            if (loading) {
                binding.counterTextView.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.incrementButton.isEnabled = false
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.counterTextView.visibility = View.VISIBLE
                binding.incrementButton.isEnabled = true
            }
        }.launchIn(lifecycleScope)  //Esta escucha activa se hace mientras la corutina está viva

        //Dispatchers.Main -> solo oara actualizaciones de UI
        //Dispatchers.Default -> para tareas costosas parael CPU pero no bloqueantes
        //Dispatchers.IO -> para lecturas y/o a red, entrada/salida
    }




}
