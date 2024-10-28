package ar.edu.unicen.movieapp.ddl.models

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val popularity: Double,
    val rating: Double,
    val voteCount: Int,
    val genreIds: List<Int>,       // IDs de los géneros
    val genres: List<String>        // Nombres de los géneros
)


