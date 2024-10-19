package ar.edu.unicen.movieapp.ddl.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("activity")
    suspend fun getRecommendation(
        @Query("participants")
        participants: Int
    ): Response<MovieRecommendationDto>

}