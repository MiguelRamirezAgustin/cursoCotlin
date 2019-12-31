package com.example.cursoretrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cursoretrofit.R
import com.example.cursoretrofit.tool.fromUrl
import kotlinx.android.synthetic.main.item_dog.view.*


class DogsAdapter (val images: List<String>) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)

        //images lisat de string
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: String) {
            //itemView es todo el recuadro o son los item
            itemView.ivDog.fromUrl(image)
        }
    }
}