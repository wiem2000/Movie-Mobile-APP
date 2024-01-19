package com.gl4.projet.restAPI

import android.app.Application
import android.content.Context
import com.gl4.projet.network.NetworkConnectionInterceptor
import com.gl4.projet.network.NetworkUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor






class RetrofitHelper(private val context: Context) {
    private val baseUrl ="https://api.themoviedb.org/3/"


    val networkUtils = NetworkUtils(context)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NetworkConnectionInterceptor(networkUtils))
        // Ajoutez d'autres intercepteurs au besoin
        .build()



    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    public val retrofitService : RestAPI by lazy { retrofit.create(RestAPI::class.java) }


}