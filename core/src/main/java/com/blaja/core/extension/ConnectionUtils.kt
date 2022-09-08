package com.blaja.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object ConnectionUtils{

    fun isOnline(context: Context): Boolean {
        var mIsConnected = false
        val mConnectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            // Checking internet connectivity
            var activeNetwork: NetworkInfo? = null
            activeNetwork = mConnectivityManager.activeNetworkInfo
            mIsConnected = activeNetwork != null
        } else {
            val allNetworks = mConnectivityManager.allNetworks // added in API 21 (Lollipop)
            for (network in allNetworks) {
                val networkCapabilities = mConnectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    )
                        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                        ) mIsConnected = true
                }
            }
        }
        return mIsConnected
    }
}
