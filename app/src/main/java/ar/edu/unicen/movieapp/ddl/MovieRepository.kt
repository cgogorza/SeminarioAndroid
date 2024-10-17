package ar.edu.unicen.movieapp.ddl

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
) {


    suspend fun getCounter(): Int{
        return movieLocalDataSource.getCounter()
    }

    suspend fun setCounter(value: Int): Boolean {
       return movieLocalDataSource.setCounter(value)
    }

    suspend fun reset(): Boolean{
        return movieLocalDataSource.reset()
    }

}