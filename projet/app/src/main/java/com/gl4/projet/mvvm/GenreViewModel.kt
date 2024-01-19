package com.gl4.projet.mvvm

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gl4.projet.MainActivity
import com.gl4.projet.models.genre_models.GenreResponse
import com.gl4.projet.network.NetworkManager
import com.gl4.projet.restAPI.RetrofitHelper
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import com.gl4.projet.network.NoNetworkException as NoNetworkException

class GenreViewModel(private val context : Context) {

    private val genreResponse = MutableLiveData<GenreResponse>()
    var genres : LiveData<GenreResponse> = genreResponse


    init {
        getGenres()
    }



    fun getGenres(){
        RetrofitHelper(context).retrofitService.getGenres().enqueue(
            object : Callback<GenreResponse> {
                override fun onResponse(
                    call: Call<GenreResponse>,
                    response: Response<GenreResponse>
                ) {
                    if(response.isSuccessful){
                        genreResponse.value = response.body()
                        genres = genreResponse
                    }
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {

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