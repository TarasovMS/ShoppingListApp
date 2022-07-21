package com.persAssistant.shopping_list.feature.connection_unavailable.delegate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.persAssistant.shopping_list.ui.activity.MainActivityViewModel
import com.persAssistant.shopping_list.util.UiRouter

interface NoInternetShower {
    fun initNoInternetShower(
        appBaseViewModel: MainActivityViewModel,
        iuRouter: UiRouter,
        lifecycleOwner: LifecycleOwner
    )

    fun observeNetworkStatus(applicationContext: Context)
    fun showNoInternet()
}
