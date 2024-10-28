package ar.edu.unicen.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.movieapp.R
import ar.edu.unicen.movieapp.databinding.ListItemMovieBinding
import ar.edu.unicen.movieapp.ddl.models.Movie
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            binding.movieTitle.text = movie.title
            binding.movieOriginalLanguage.text = "Idioma: ${movie.originalLanguage}"

            val imageUrl = movie.picture.posterThumbnail
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_movie_logo)
                .error(R.drawable.ic_image_broken)
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }
}
