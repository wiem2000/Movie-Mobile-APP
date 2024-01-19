package com.gl4.projet.mvvm

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gl4.projet.config
import com.gl4.projet.models.movie_models.MovieResponse
import com.gl4.projet.network.NetworkManager
import com.gl4.projet.network.NoNetworkException
import com.gl4.projet.restAPI.RetrofitHelper
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val context : Context) {
    private val movieResponse = MutableLiveData<MovieResponse>()
    var movies : LiveData<MovieResponse> = movieResponse



    private var currentPage = 1
    private var totalPages = 1



    fun getMovies(genre_id:String,page:Int){


                RetrofitHelper(context).retrofitService.getMovieByGenre(genre_id, page).enqueue(
                    object : Callback<MovieResponse> {
                        override fun onResponse(
                            call: Call<MovieResponse>,
                            response: Response<MovieResponse>
                        ) {
                            if (response.isSuccessful) {
                                movieResponse.value = response.body()
                                movies = movieResponse
                                totalPages = movies.value!!.total_pages.toInt()
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                            if(t is NoNetworkException) {
                                // show No Connectivity message to user or do whatever you want.

                                val rootView: View = (context as Activity).findViewById(android.R.id.content)
                                Snackbar.make(rootView, "No Internet Connection", Snackbar.LENGTH_LONG).show()

                            }
                            println("Failed to load data. Error: ${t.message}")
                        }
                    }
                )

            }




    fun searchMovie(query:String,page:Int){
        RetrofitHelper(context).retrofitService.searchMovie(query, page).enqueue(
            object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if(response.isSuccessful){
                        movieResponse.value = response.body()
                        movies = movieResponse
                        totalPages=movies.value!!.total_pages.toInt()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                    if(t is NoNetworkException) {
                        // show No Connectivity message to user or do whatever you want.

                        val rootView: View = (context as Activity).findViewById(android.R.id.content)
                        Snackbar.make(rootView, "No Internet Connection", Snackbar.LENGTH_LONG).show()

                    }
                    println("Failed to load data. Error: ${t.message}")
                }
            }
        )
    }

    fun goNextpage(genre_id:String){
        if (currentPage < totalPages) {
            currentPage++
        getMovies(genre_id,currentPage)
        }
    }
    fun goPreviouspage(genre_id:String){
        if (currentPage >1 ) {
            currentPage--
            getMovies(genre_id,currentPage)
        }
    }


}