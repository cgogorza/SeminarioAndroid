package ar.edu.unicen.movieapp.ddl

import android.content.SharedPreferences
import ar.edu.unicen.movieapp.di.MovieSharedPreference
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class MovieLocalDataSource @Inject constructor(         //una instancia de localdsource paracada view model
    @MovieSharedPreference
    private val sharedPreferences: SharedPreferences
    ) {

//este LocalDataSource nunca hará tareas bloqueantes en un MainDistpacher, lo hará en un IO

        suspend fun getCounter(): Int{
            return  withContext(Dispatchers.IO){
                sharedPreferences.getInt("counter", 0)
            }
        }

        suspend fun setCounter(value: Int): Boolean { //función suspend: se que estoy dentro de una corutina
            return withContext(Dispatchers.IO) {
               try {
                   delay(1000)
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putInt("counter",value)
                    editor.commit()
                } catch (e: Exception){
                    e.printStackTrace()
                    false        //tomo la excepción y evito que vaya al ViewModel
                }
           }
        }


        suspend fun reset(): Boolean{
            return  withContext(Dispatchers.IO){
                try {
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()
                    editor.clear() //lo hago así porque este sharedpreference es solo para este data source
                    editor.commit()
                } catch (e: Exception){
                    e.printStackTrace()
                    false
                }

            }
        }

}