package com.gl4.projet.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.gl4.projet.R
import com.gl4.projet.config
import com.gl4.projet.detail
import com.gl4.projet.models.movie_models.MovieResponse
import com.squareup.picasso.Picasso

import java.util.*

class MovieAdapter(private val movieResponse : MovieResponse?,private val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView
        val release_date : TextView
        val vote_average : TextView
        val movie_poster: ImageView

        init {
            title = itemView.findViewById(R.id.movie_title)
            release_date= itemView.findViewById(R.id.release_date)
            vote_average = itemView.findViewById(R.id.vote_average)
            movie_poster=itemView.findViewById(R.id.movie_poster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movieItem = movieResponse!!.results[position]

        holder.title.text = movieItem.title.toString()
        holder.release_date.text = "Release Date : ${movieItem.release_date.toString()} "

        holder.vote_average.text = "Vote Average : ${((movieItem.vote_average*10).toInt()).toString()} %"

        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2"+movieItem.poster_path.toString()).into(holder.movie_poster)

        holder.itemView.setOnClickListener {

            val intent = Intent(context, detail::class.java)
            intent.putExtra("movie_id", movieItem.id.toString())

            context.startActivity(intent)
        }


    }



    override fun getItemCount(): Int {
        if(movieResponse != null) {

            return movieResponse.results.size
        }

        return 0
    }


}