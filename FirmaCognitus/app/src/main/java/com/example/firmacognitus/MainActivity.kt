package com.example.firmacognitus

import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.gcacace.signaturepad.views.SignaturePad
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    //porque son contantes
    companion object {
        val TAG = "PermissionDemo"
        private val REQUEST_PERMISSION = 2000
        private const val REQUEST_INTERNET = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        revisaPermiso()

        signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
                //cuando comienza a dibujar
                Toast.makeText(
                    applicationContext,
                    "comienza firma",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onSigned() {
                //cuando termina de firmar
                mSaveButton.isEnabled = true
                mClearButton.isEnabled = true
            }

            override fun onClear() {
                //deshabilita
                mSaveButton.isEnabled = false
                mClearButton.isEnabled = false
            }
        })

        mSaveButton.setOnClickListener {
            val signatureBitmap: Bitmap = signaturePad.transparentSignatureBitmap
            if (addJpgSignatureToGallery(signatureBitmap)) {
                Toast.makeText(this@MainActivity,
                    "Guardar Firma",
                    Toast.LENGTH_SHORT
                ).show()
                signaturePad.clear()
            } else {
                Toast.makeText(
                    this,
                    "No se puede almacenar la firma",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        mClearButton.setOnClickListener { signaturePad.clear() }

    }





    fun revisaPermiso(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_INTERNET
            )
            Log.i(TAG, "Pide permiso")
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_INTERNET -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Si dio permiso")
            }else{
                Log.i(TAG, "No dio permiso")
                //Permisos necesarios
                revisaPermiso()
            }
        }
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val path =
                Environment.getExternalStorageDirectory().toString() + "/checador"
            Log.d("Files", "Path: $path")
            val fileFirm = File(path)
            fileFirm.mkdirs()
            val photo =
                File(fileFirm, "Firma.png")
            Log.d("Files", "Path: $photo")
            saveBitmapToPNG(signature, photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    @Throws(IOException::class)
    fun saveBitmapToPNG(bitmap: Bitmap, photo: File) {
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(photo)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}

