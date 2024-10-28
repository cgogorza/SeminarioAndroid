package ar.edu.unicen.movieapp.ddl.data.dto

data class PictureDto(
    private val posterPath: String?,
    private val backdropPath: String?
) {
    private val baseUrl = "https://image.tmdb.org/t/p/"

    val posterThumbnail: String? get() = posterPath?.let { "$baseUrl/w92$it" }
    val posterMedium: String? get() = posterPath?.let { "$baseUrl/w185$it" }
    val posterLarge: String? get() = posterPath?.let { "$baseUrl/w500$it" }

    val backdropThumbnail: String? get() = backdropPath?.let { "$baseUrl/w300$it" }
    val backdropMedium: String? get() = backdropPath?.let { "$baseUrl/w780$it" }
    val backdropLarge: String? get() = backdropPath?.let { "$baseUrl/w1280$it" }
}
