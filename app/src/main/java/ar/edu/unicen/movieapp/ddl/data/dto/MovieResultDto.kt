package ar.edu.unicen.movieapp.ddl.data.dto

import com.google.gson.annotations.SerializedName

data class MovieResultDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)


