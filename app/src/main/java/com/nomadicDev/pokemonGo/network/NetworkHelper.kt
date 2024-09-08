package com.nomadicDev.pokemonGo.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import retrofit2.Response


inline fun <T> performCall(context : Context, apiCall: () -> Response<T>): NetworkResult<T> {
    if (isNetworkAvailable(context)) {
        Log.d("performCall", "Network is available")
    } else {
        Log.d("performCall", "Network is not available")
        return NetworkResult.Error("Network is not available")
    }

    try {
        val response = apiCall()
        return when {
            response.isSuccessful -> {
                val body = response.body()
                NetworkResult.Success(body)
            }
            else -> {
                Log.d("performCall", "safeCall not success: ${response.errorBody()}")
                NetworkResult.Error("Api Failed: ${response.errorBody()}")
            }
        }
    } catch (e: Exception) {
        Log.d("performCall", "exceptionMessage: ${e.message}")
        return error(e.message.toString())
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
