package com.example.cursonoticiascognito.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursonoticiascognito.R
import com.example.cursonoticiascognito.adapter.CasaAdapter

class Activity_Casa : AppCompatActivity() {

    val casaList: ArrayList<String> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casa)

        setTitle("Casas disponibles")

        casaList.add("Casa número 1")
        casaList.add("Casa numero 2")
        casaList.add("Casa número 3")

        linearLayoutManager = LinearLayoutManager(this)

        val rvCasas = findViewById<RecyclerView>(R.id.rvCasas)
        rvCasas!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvCasas.layoutManager = linearLayoutManager
        rvCasas.adapter = CasaAdapter(this, casaList)
    }
}
