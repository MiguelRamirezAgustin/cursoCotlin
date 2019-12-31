package com.example.cursonoticiascognito.utilities

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class Utils {

    //companion para poder usar las function en otra clase
    companion object{

        val EMAIL_REGEX =  "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        //function para validar correo
        fun isEmailValid(email:String):Boolean{
            return EMAIL_REGEX.toRegex().matches(email)
        }

        //Verificar internet
        fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
            val connectivityManager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }


    }
}