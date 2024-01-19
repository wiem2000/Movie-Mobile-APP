package com.gl4.projet.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi

import androidx.lifecycle.LiveData



class NetworkManager(context: Context):LiveData<Boolean>() {





    @RequiresApi(Build.VERSION_CODES.M)
    private var connectivityManager=context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager



    private  val networkCallBack=object :ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            postValue(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()

            postValue(false)

        }

        override fun onLost(network: Network) {
            super.onLost(network)

            postValue(false)
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private  fun checkNetworkConnectivity(){
        val network=connectivityManager.activeNetwork
        if(network==null){

            postValue(false)
        }
        val requestBuilder=NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()

        connectivityManager.registerNetworkCallback(requestBuilder,networkCallBack)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActive() {
        super.onActive()
        checkNetworkConnectivity()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallBack)
    }




}

