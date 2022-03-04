package com.persAssistant.shopping_list.ui

import com.persAssistant.shopping_list.di.AppComponent
import com.persAssistant.shopping_list.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        initDagger()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
        return appComponent
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.
        builder()
            .application(this)
            .context(this)
            .build()
    }
}