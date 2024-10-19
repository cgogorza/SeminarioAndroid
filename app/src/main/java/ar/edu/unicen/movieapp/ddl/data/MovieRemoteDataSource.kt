package ar.edu.unicen.movieapp.ddl.data

import ar.edu.unicen.movieapp.ddl.models.MovieRecommendation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getRecommendation(
        participants: Int
    ): MovieRecommendation? {
        return withContext(Dispatchers.IO) {
            try{
                val response = movieApi.getRecommendation(participants)
                val movieRecommendation = response.body()?.toMovieRecommendation()

                return@withContext movieRecommendation
            } catch (e:Exception){
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}
