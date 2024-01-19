package com.gl4.projet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView

import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.projet.adapters.MovieAdapter
import com.gl4.projet.databinding.ActivityConfigBinding

import com.gl4.projet.mvvm.MovieViewModel
import com.gl4.projet.network.NetworkManager
import com.gl4.projet.network.NoNetworkException
import com.google.android.material.snackbar.Snackbar


class config : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    var movieViewModel: MovieViewModel = MovieViewModel(this)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val genre_name = intent.getStringExtra("genre_name")
        val genre_id = intent.getStringExtra("genre_id")
        binding.tvGenre.text = "Genre : " + genre_name.toString()


        if (genre_id != null) {

            movieViewModel.getMovies(genre_id, 1)
        }


        updateRecyclerView()

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Handle search query submission
                    if (query != null) {
                        movieViewModel.searchMovie(query, 1)
                    }

                    updateRecyclerView()

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Handle changes in the search query text if needed
                    return true
                }
            })


        binding.searchView.setOnCloseListener {

            if (genre_id != null) {
                movieViewModel.getMovies(genre_id, 1)
            }
            updateRecyclerView()
            false
        }



        binding.btnNext.setOnClickListener {
            if (genre_id != null) {
                movieViewModel.goNextpage(genre_id)

            }

        }

        binding.btnPrevious.setOnClickListener {
            if (genre_id != null) {
                movieViewModel.goPreviouspage(genre_id)
            }
        }


    }

    fun updateRecyclerView() {

        movieViewModel.movies.observe(this@config) {
            if (it != null) {
                binding.rvMovies.adapter = MovieAdapter(movieViewModel.movies.value, this@config)

            }
        }

        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@config)
            adapter = MovieAdapter(movieViewModel.movies.value, this@config)
        }

    }

    /*
    fun checkInternetConnexion(){

        val networkManager = NetworkManager(this@config)

        networkManager.observe(this@config){
            if(!it){
                Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_INDEFINITE).show()
                throw NoNetworkException("No internet connection")
            }
            else{
                Snackbar.make(binding.root, "Internet connection available", Snackbar.LENGTH_SHORT).show()

            }
        }
    }

     */
}