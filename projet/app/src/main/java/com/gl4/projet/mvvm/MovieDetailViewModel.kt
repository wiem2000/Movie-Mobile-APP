package com.gl4.projet.mvvm

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gl4.projet.models.movie_detail_models.MovieDetailResponse
import com.gl4.projet.network.NoNetworkException
import com.gl4.projet.restAPI.RetrofitHelper
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val context : Context) {

    private val movieDetailResponse = MutableLiveData<MovieDetailResponse>()
    var movieDetails : LiveData<MovieDetailResponse> = movieDetailResponse


    fun getMovieDetails(movie_id:String){
        RetrofitHelper(context).retrofitService.getMovieById(movie_id).enqueue(
            object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if(response.isSuccessful){
                        movieDetailResponse.value = response.body()
                        movieDetails = movieDetailResponse

                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {

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
}