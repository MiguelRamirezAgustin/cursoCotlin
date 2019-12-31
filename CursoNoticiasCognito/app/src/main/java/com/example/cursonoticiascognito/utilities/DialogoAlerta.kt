package com.example.cursonoticiascognito.utilities

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.cursonoticiascognito.R
import kotlinx.android.synthetic.main.view_dialog.view.*


object DialogoAlerta {
    
    fun crearDialogo(context: Context, titulo: String, mensaje: String, tituloAD :String) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.view_dialog, null)
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(context).setView(mDialogView)
        mDialogView.tvMsg?.text=mensaje
        mDialogView.tvTitulo?.text=titulo
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(tituloAD)
        // show alert dialog
        alert.show()
        mDialogView.btnAcepta.setOnClickListener {
            //dismiss dialog
            alert.dismiss()
        }
        //cancel button click of custom layout
        mDialogView.btnCancel.setOnClickListener {
            //dismiss dialog
            alert.hide()
        }
    }
}
