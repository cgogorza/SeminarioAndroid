package ar.edu.unicen.movieapp.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ar.edu.unicen.movieapp.BuildConfig
import ar.edu.unicen.movieapp.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()  // Configura el RecyclerView según la orientación
        suscribeToUi()
        suscribeToViewModel()
    }
    private fun setupRecyclerView() {
        // Detecta la orientación de la pantalla
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 2 else 1
        binding.moviesList.layoutManager = GridLayoutManager(this, spanCount)
    }

    private fun suscribeToUi() {
        binding.loadMoviesButton.setOnClickListener {
            // Definir cantidad total de películas y número de páginas a cargar
            val quantity = 100
            val pages = 2

            viewModel.getPopularMovies(quantity, pages)
        }
    }

    private fun suscribeToViewModel() {
        viewModel.loading.onEach { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
            binding.loadMoviesButton.isEnabled = !loading
        }.launchIn(lifecycleScope)

        viewModel.movies.onEach { movies ->
            binding.moviesList.adapter = MovieAdapter(
                movies ?: emptyList(),
                onMovieClick = { movie ->
                    val intent = Intent(this, MovieDetailsActivity::class.java)
                    intent.putExtra("movie_title", movie.title)
                    intent.putExtra("movie_rating", movie.rating)
                    intent.putExtra("movie_genres", movie.genres.joinToString(", "))
                    intent.putExtra("movie_overview", movie.overview)
                    intent.putExtra("movie_poster_url", movie.picture.backdropMedium)
                    startActivity(intent)
                }
            )
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            binding.error.visibility = if (error) View.VISIBLE else View.INVISIBLE
        }.launchIn(lifecycleScope)
    }
}
