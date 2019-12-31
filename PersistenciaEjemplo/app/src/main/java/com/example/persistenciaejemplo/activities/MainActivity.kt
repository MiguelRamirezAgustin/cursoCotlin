package com.example.persistenciaejemplo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.persistenciaejemplo.R
import com.example.persistenciaejemplo.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = SharedPreference(this)

        btnSave.setOnClickListener {

            val name = etName.text
            val email = etMail.text
            // string con variables locales
            //sharedPreference.save("name", name.toString())
            //sharedPreference.save("email", email.toString())

            //string ocn variables usando archivo
            sharedPreference.save( getString(R.string.pref_name), name.toString())
            sharedPreference.save(getString(R.string.pref_email), email.toString())
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
        }
        btnRetrive.setOnClickListener {
            if (sharedPreference.getValueString("name") != null) {
                //etName?.text= sharedPreference.getValueString("name")!!.toEditable()
                // usando variables de string
                etName?.text= sharedPreference.getValueString(getString(R.string.app_name))!!.toEditable()

            } else {
                etName?.text = "No existe ese valor".toEditable()
            }
            if (sharedPreference.getValueString("email") != null) {
                //etMail?.text = sharedPreference.getValueString("email")!!.toEditable()
                // usando variables de string
                etMail?.text = sharedPreference.getValueString(getString(R.string.pref_email))!!.toEditable()
            } else {
                etMail?.text = "No value found".toEditable()
            }
        }
        btnClear.setOnClickListener {
            sharedPreference.clearSharedPreference()
            etName?.text=null
            etMail?.text=null
            Toast.makeText(this, "Datos eliminados", Toast.LENGTH_SHORT).show()
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
