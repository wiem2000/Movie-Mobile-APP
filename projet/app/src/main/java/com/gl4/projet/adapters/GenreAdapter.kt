package com.gl4.projet.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.gl4.projet.R
import com.gl4.projet.config
import com.gl4.projet.models.genre_models.GenreResponse


class GenreAdapter(private val genreResponse: GenreResponse?,private val context: Context) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val genre_name : TextView

        init {
            genre_name = itemView.findViewById(R.id.tv_language)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val genre_item = genreResponse!!.genres[position]
        holder.genre_name.text = genre_item.name.toString()
        holder.itemView.setOnClickListener {

            val intent = Intent(context, config::class.java)


            intent.putExtra("genre_name", genre_item.name.toString())
            intent.putExtra("genre_id", genre_item.id.toString())

            context.startActivity(intent)
        }


    }



    override fun getItemCount(): Int {
        if(genreResponse != null) {

            return genreResponse.genres.size
        }

        return 0
    }


}