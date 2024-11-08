package ar.edu.unicen.movieapp.di

import ar.edu.unicen.movieapp.ddl.data.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ViewModelComponent::class) //acá se controla tiempo de vida
class MovieModule {

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieApi(
        retrofit: Retrofit
    ): MovieApi {
        return retrofit.create(MovieApi::class.java);
    }

}

