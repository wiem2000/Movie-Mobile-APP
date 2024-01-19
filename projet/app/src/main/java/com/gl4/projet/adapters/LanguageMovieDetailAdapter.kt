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

import com.gl4.projet.models.movie_detail_models.MovieDetailResponse

class LanguageMovieDetailAdapter(private val languageResponse: MovieDetailResponse?, private val context: Context) : RecyclerView.Adapter<LanguageMovieDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val language_name : TextView

        init {
            language_name = itemView.findViewById(R.id.tv_language)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.language_item_movie_detail,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val language_item = languageResponse!!.spoken_languages[position]
        holder.language_name.text = language_item.english_name.toString()



    }



    override fun getItemCount(): Int {
        if(languageResponse!= null) {

            return languageResponse!!.spoken_languages.size
        }

        return 0
    }

}