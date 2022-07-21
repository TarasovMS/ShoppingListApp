package com.persAssistant.shopping_list.ui

import com.persAssistant.shopping_list.BuildConfig
import com.persAssistant.shopping_list.di.AppComponent
import com.persAssistant.shopping_list.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.sentry.android.core.SentryAndroid

class App: DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAnalyticsTools()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
        return appComponent
    }

    private fun initAnalyticsTools() {
        // помимо Sentry надо еще FireBase
        SentryAndroid.init(this) { options ->
            options.environment = BuildConfig.BUILD_TYPE
            options.isDebug = true
        }
    }
}