package ar.edu.unicen.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class) //acá se controla tiempo de vida
class MovieModule {

    @Provides
    @MovieSharedPreference
    @ViewModelScoped        //controla que se cree una sola instancia
    fun provideSharedPreference(
        @ApplicationContext
        context: Context
    ): SharedPreferences {
        return  context.getSharedPreferences("counter", Context.MODE_PRIVATE)
    }

}