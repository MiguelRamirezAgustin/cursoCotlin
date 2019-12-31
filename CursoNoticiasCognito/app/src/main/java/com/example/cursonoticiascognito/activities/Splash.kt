package com.example.cursonoticiascognito.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cursonoticiascognito.R

class Splash : AppCompatActivity(),
    Runnable {
    //Ejecucion de splash usando hilos

    private  lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        simulaCarga()
    }

    private fun simulaCarga(){
      handler = Handler()
      handler.postDelayed(this, 3000)
    }

    override fun run() {
        //vslida sesion
        val sharedPreferences = getSharedPreferences(
            "mi_app_cognitus",
            Context.MODE_PRIVATE
        )
        val usrId = sharedPreferences.getString("usr_id", "")
        Log.i("Valor de UsrId", "-> "+ usrId)
        //existe sesion
        if(!usrId.equals("")){
            val inten = Intent(this@Splash, MenuActivity::class.java)
            inten.putExtra("nombre_user", ""+usrId)
            startActivity(inten)
            finish()
        }else{
            //no hay sesion
            val intent = Intent(this@Splash,  MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}
