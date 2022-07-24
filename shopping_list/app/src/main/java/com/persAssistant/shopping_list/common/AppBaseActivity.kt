package com.persAssistant.shopping_list.common

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.feature.connection_unavailable.delegate.NoInternetShower
import com.persAssistant.shopping_list.feature.connection_unavailable.delegate.NoInternetShowerImpl
import com.persAssistant.shopping_list.util.UiRouter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AppBaseActivity: DaggerAppCompatActivity(),
    NoInternetShower by NoInternetShowerImpl() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val navController: NavController by lazy { findNavController(R.id.activity_main_nav_host_fragment) }
    protected val uiRouter by lazy { UiRouter(navController) }
//    protected lateinit var app: App

    abstract fun initNetworkObserver(uiRouter: UiRouter)

    override fun onResume() {
        super.onResume()
        //TODO ЕСли надо подключить проверку на интернет и вывести экран
//        initNetworkObserver(UiRouter(navController))
    }

}
