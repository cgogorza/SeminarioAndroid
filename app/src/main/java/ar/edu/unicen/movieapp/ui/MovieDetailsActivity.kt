package ar.edu.unicen.movieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unicen.movieapp.databinding.ActivityMovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("movie_title")
        val rating = intent.getDoubleExtra("movie_rating", 0.0)
        val genres = intent.getStringExtra("movie_genres")
        val overview = intent.getStringExtra("movie_overview")
        val posterUrl = intent.getStringExtra("movie_poster_url")

        binding.movieTitle.text = title
        binding.movieRating.text = "Rating: ${rating ?: "N/A"}"
        binding.movieGenres.text = "Genres: ${genres.orEmpty().ifEmpty { "N/A" }}"
        binding.movieSynopsis.text = overview

        Glide.with(this)
            .load(posterUrl)
            .into(binding.moviePoster)


        binding.btnBack.setOnClickListener {
            val intent = Intent(this,MoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        }
    }


