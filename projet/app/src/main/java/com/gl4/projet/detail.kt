package com.gl4.projet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager


import com.gl4.projet.adapters.GenreMovieDetailAdapter
import com.gl4.projet.adapters.LanguageMovieDetailAdapter


import com.gl4.projet.databinding.ActivityDetailBinding
import com.gl4.projet.models.movie_detail_models.MovieDetailResponse

import com.gl4.projet.mvvm.MovieDetailViewModel

import com.squareup.picasso.Picasso

class detail : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    var movieDetailViewModel : MovieDetailViewModel = MovieDetailViewModel(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movie_id=intent.getStringExtra("movie_id")

        if(movie_id != null){
            movieDetailViewModel.getMovieDetails(movie_id)
        }

        movieDetailViewModel.movieDetails.observe(this) {
            if (it != null){
                val movieDetailResponse : MovieDetailResponse?= movieDetailViewModel.movieDetails.value
               if(movieDetailResponse!=null) {

                   binding.movieTitle.text = movieDetailResponse.title.toString()

                   binding.releaseDate.text = "${movieDetailResponse.release_date.toString()} "

                   binding.runtime.text = convertMinutesToHoursAndMinutes(movieDetailResponse.runtime.toString())

                   binding.voteAverage.text = ((movieDetailResponse.vote_average * 10).toInt()).toString()+" %"
                   binding.progressBar.progress=(movieDetailResponse.vote_average * 10).toInt()

                   binding.overview.text = movieDetailResponse.overview.toString()

                   Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movieDetailResponse.poster_path.toString()).into(binding.posterPath)

                   binding.rvGenre.adapter = GenreMovieDetailAdapter(movieDetailViewModel.movieDetails.value,this@detail)

                   binding.rvLanguage.adapter = LanguageMovieDetailAdapter(movieDetailViewModel.movieDetails.value,this@detail)


               }



            }
        }


        binding.rvGenre.apply {
            layoutManager = LinearLayoutManager(this@detail,LinearLayoutManager.HORIZONTAL, false)
            adapter = GenreMovieDetailAdapter(movieDetailViewModel.movieDetails.value,this@detail)
        }

        binding.rvLanguage.apply {
            layoutManager = LinearLayoutManager(this@detail,LinearLayoutManager.HORIZONTAL, false)
            adapter = LanguageMovieDetailAdapter(movieDetailViewModel.movieDetails.value,this@detail)
        }

    }

    fun convertMinutesToHoursAndMinutes(minutes: String): String {

        val hours = minutes.toInt() / 60
        val remainingMinutes = minutes.toInt() % 60

        return if (hours > 0) {
            "${hours} h ${remainingMinutes}m"
        } else {
            "${remainingMinutes} m"
        }
    }
}