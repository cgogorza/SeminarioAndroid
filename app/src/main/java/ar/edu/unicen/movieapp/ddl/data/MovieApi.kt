package ar.edu.unicen.movieapp.ddl.data

import ar.edu.unicen.movieapp.ddl.data.dto.GenreResponse
import ar.edu.unicen.movieapp.ddl.data.dto.MovieResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authToken: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResultDto>

    // Endpoint para obtener la lista de géneros de películas
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Header("Authorization") authToken: String,
        @Query("language") language: String = "en-US"
    ): Response<GenreResponse>
}

