package com.example.cursonoticiascognito.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursonoticiascognito.R
import com.example.cursonoticiascognito.activities.DetailNewsActivity
import com.example.cursonoticiascognito.activities.NoticiasListaActivity
import com.example.cursonoticiascognito.activities.WebviewActivity
import com.example.cursonoticiascognito.model.NoticiaModel
import kotlinx.android.synthetic.main.item_noticia.view.*

class NoticiaAdapter(private val context: NoticiasListaActivity, private val noticiasList: List<NoticiaModel>) :
    RecyclerView.Adapter<NoticiaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false))
    }
    override fun getItemCount(): Int {
        return noticiasList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticiaModel=noticiasList.get(position)
        holder.tituloNoticia?.text = noticiaModel.notTitulo
        val requestManager = Glide.with(context)
        val imageUri = noticiaModel.notImg
        val requestBuilder = requestManager.load(imageUri)
        requestBuilder.into(holder.fotoNotica)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra("img_url", ""+noticiaModel.notImg)
            intent.putExtra("title", ""+noticiaModel.notTitulo)
            intent.putExtra("news_url", ""+noticiaModel.notUrl)
            context.startActivity(intent)
        }
        holder.ivVerMas.setOnClickListener {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("news_url", ""+noticiaModel.notUrl)
            context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloNoticia = view.tvTitulo
        val fotoNotica = view.ivFotoNot
        val ivVerMas = view.ivVerMas
    }
}