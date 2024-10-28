package ar.edu.unicen.movieapp.ddl.data.dto

import ar.edu.unicen.movieapp.ddl.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("genre_ids") val genreIds: List<Int>
)


fun MovieDto.toMovie(genreMap: Map<Int, String>): Movie {
    val genreNames = genreIds.mapNotNull { genreMap[it] }

    return Movie(
        id = id,
        title = title,
        picture = PictureDto(posterPath, backdropPath), // Asigna PictureDto para manejar múltiples tamaños
        originalLanguage = originalLanguage,
        overview = overview,
        releaseDate = releaseDate,
        popularity = popularity,
        rating = voteAverage,
        voteCount = voteCount,
        genreIds = genreIds,
        genres = genreNames
    )
}


