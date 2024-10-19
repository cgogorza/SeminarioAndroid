package ar.edu.unicen.movieapp.ddl.data

import ar.edu.unicen.movieapp.ddl.models.MovieRecommendation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun getRecommendation(
        participants: Int
    ): MovieRecommendation? {
        return movieRemoteDataSource.getRecommendation(participants)
    }

}
