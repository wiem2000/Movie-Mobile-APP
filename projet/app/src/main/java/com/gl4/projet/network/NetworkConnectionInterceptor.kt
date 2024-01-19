package com.gl4.projet.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val networkUtils: NetworkUtils ): Interceptor {

    /*
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoNetworkException("No Internet Connection")

        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
*/

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.isInternetAvailable()) {
            throw NoNetworkException("No Internet Connection")
        }

        val request: Request = chain.request()
        return chain.proceed(request)
    }
}


