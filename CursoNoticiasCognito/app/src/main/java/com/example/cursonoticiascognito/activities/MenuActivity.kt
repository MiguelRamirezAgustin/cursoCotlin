package com.example.cursonoticiascognito.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cursonoticiascognito.R
import com.example.cursonoticiascognito.utilities.DialogoAlerta

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnCierra = findViewById<Button>(R.id.btnCierra)
        val tVUser = findViewById<TextView>(R.id.tVUsuarioParam)
        val LNoticia = findViewById<LinearLayout>(R.id.btn_Noticia)
        val liAcerca = findViewById<LinearLayout>(R.id.LinearAcerca)

        val dataName = intent.getStringExtra("nombre_user")

        tVUser.setText(dataName)


        btnCierra.setOnClickListener{
            val builder = AlertDialog.Builder(this@MenuActivity)
            builder.setTitle("Alerta")
            builder.setMessage("¿Desea cerrar su sesion?")
            builder.setPositiveButton("Si"){dialog, which ->
                //cerrar sesion limpia
                val sharedPreferences = getSharedPreferences("mi_app_cognitus", Context.MODE_PRIVATE)
                var editor = sharedPreferences.edit()
                editor.putString("usr_id", "")
                editor.commit()
                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("No"){dialog, which ->  }
            val dialog:AlertDialog= builder.create()
            dialog.show()
        }


        LNoticia.setOnClickListener{
            val intent = Intent(this, NoticiasListaActivity::class.java)
            startActivity(intent)

        }

        liAcerca.setOnClickListener {
                DialogoAlerta.crearDialogo(this,"Atención", "Ud ha dado clic en el botón","Alerta")
        }



    }
}
