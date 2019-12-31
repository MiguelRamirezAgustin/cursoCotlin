package com.example.cursonoticiascognito.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cursonoticiascognito.R
import com.example.cursonoticiascognito.task.ApiGetPostHelper
import com.example.cursonoticiascognito.utilities.Utils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var strUsr: String
    lateinit var strNip: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAceptar = findViewById<Button>(R.id.btn_Aceptar)
        val eTUsuario = findViewById<EditText>(R.id.eTUsuario)
        val eTContraseña = findViewById<EditText>(R.id.eTContraseña)


        btnAceptar.setOnClickListener{
            if (eTUsuario.text.isEmpty() || eTContraseña.text.isEmpty()){
                Toast.makeText(this@MainActivity, "Verifique que los campos no esten vaciós" , Toast.LENGTH_SHORT).show()
            }else{
                if(Utils.isEmailValid(""+ eTUsuario.text)){

                    //aguardar sesion y se valida en splash
                    if (Utils.verifyAvailableNetwork(this)) {
                        // oculta el teclado
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        strUsr = eTUsuario.text.toString()
                        strNip = eTContraseña.text.toString()
                      // Call AsyncTask for getting list from server in JSON format
                        getUsuarioIngreso().execute()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "¡No cuenta con conexión a Internet!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }else {
                    Toast.makeText(this@MainActivity, "El correo no cumple con el formato" , Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    // Inner class Async to consume WS
    @SuppressLint("StaticFieldLeak")
    inner class getUsuarioIngreso : AsyncTask<Void, Void, String>() {

        //ciclo de peticion 01
        override fun onPreExecute() {
            // show progressbar for UI experience
            pBar.visibility = View.VISIBLE
        }
        //ciclo de peticion 02
        override fun doInBackground(vararg voids: Void): String {
            // Here is post Json api example
            val sendParams = HashMap<String, String>()
            // Send parameters and value on JSON api
            sendParams["valor"] = ""
            // send the HttpPostRequest by HttpURLConnection and receive a Results in return string
            return ApiGetPostHelper.SendParams(
                getString(R.string.ws_url_login) + "getLoginUsr&email=" + strUsr + "&nip=" + strNip,
                sendParams
            )
        }

        //ciclo de peticion 03
        override fun onPostExecute(results: String?) {
            // Hide Progressbar
            pBar.visibility = View.GONE

            if (results != null) {
                // See Response in Logcat for understand JSON Results
                Log.i("Resultado: ", results)
            }
            try {
                // create JSONObject from string response
                val rootJsonObject = JSONObject(results)
                val validoObject = rootJsonObject.getString("valido")
                if (validoObject == "1") {
                    val usuarioObject = rootJsonObject.getString("usuario")
                    val mItemObject = JSONObject(usuarioObject)

                    val usrIdS = mItemObject.getString("usr_id")
                    val usrNombre = mItemObject.getString("usr_nombre")

                    val sharedPreference =  getSharedPreferences("mi_app_cognitus",Context.MODE_PRIVATE)
                    var editor = sharedPreference.edit()
                    editor.putString("usr_id",usrIdS)
                    editor.putString("usr_name",usrNombre)
                    editor.commit()

                    val intent = Intent(applicationContext, MenuActivity::class.java)
                    intent.putExtra("nombre_usr",  usrNombre)
                    intent.putExtra("usr_id", usrIdS)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Datos incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: JSONException) {
                Toast.makeText(
                    applicationContext,
                    "Lo sentimos, algo salio mal. ¡Intenta de nuevo!",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }


}
