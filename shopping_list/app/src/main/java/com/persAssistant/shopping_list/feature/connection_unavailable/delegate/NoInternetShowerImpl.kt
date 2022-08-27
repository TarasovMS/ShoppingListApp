package com.persAssistant.shopping_list.feature.connection_unavailable.delegate

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.ui.activity.MainActivityViewModel
import com.persAssistant.shopping_list.util.UiRouter

class NoInternetShowerImpl : NoInternetShower {

    private lateinit var iuRouter: UiRouter
    private lateinit var appBaseViewModel: MainActivityViewModel
    private lateinit var lifecycleOwner: LifecycleOwner

    override fun initNoInternetShower(
        appBaseViewModel: MainActivityViewModel,
        iuRouter: UiRouter,
        lifecycleOwner: LifecycleOwner
    ) {
        this.appBaseViewModel = appBaseViewModel
        this.iuRouter = iuRouter
        this.lifecycleOwner = lifecycleOwner
    }

    override fun observeNetworkStatus(applicationContext: Context) {
        setObserver()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            setNetworkCallback(applicationContext)
        }
    }

    private fun setObserver() {
        appBaseViewModel.networkStateLiveData.observe(lifecycleOwner) {
            if (it) onConnected() else onLost()
        }
    }

    private fun onConnected() {
        if (iuRouter.navController.currentDestination?.id == R.id.connectionUnavailableFragment)
            iuRouter.navigateBack()
    }

    private fun onLost() {
        if (iuRouter.navController.currentDestination?.id != R.id.connectionUnavailableFragment)
            showNoInternet()
    }

    override fun showNoInternet() {
        iuRouter.navigateToNoInternetFragment()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setNetworkCallback(applicationContext: Context) {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(
            object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    appBaseViewModel.networkStateLiveData.postValue(true)
                }

                override fun onLost(network: Network) {
                    appBaseViewModel.networkStateLiveData.postValue(false)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    Log.e(
                        "NoInternetShowerImpl",
                        "The default network changed capabilities: $networkCapabilities"
                    )
                }

                override fun onLinkPropertiesChanged(
                    network: Network,
                    linkProperties: LinkProperties
                ) {
                    Log.e(
                        "NoInternetShowerImpl",
                        "The default network changed link properties: $linkProperties"
                    )
                }
            }
        )
    }
}
