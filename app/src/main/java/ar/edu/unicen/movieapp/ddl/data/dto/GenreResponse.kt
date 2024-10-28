package ar.edu.unicen.movieapp.ddl.data.dto

data class GenreResponse(
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)
