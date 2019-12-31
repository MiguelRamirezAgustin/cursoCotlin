package com.mobtk.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.mobtk.firebasedemo.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        EventBus.getDefault().register(this)
    }

    //Aca se puede leer la notificacion
    @Subscribe
    fun enNotificacionRecibida(event: Notificacion){
        binding.titulo = event.titulo
        binding.mensaje = event.mensaje
        binding.datos = Gson().toJson(event.datos)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}