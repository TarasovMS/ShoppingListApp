package com.persAssistant.shopping_list.feature.splash.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeSplash {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            SplashViewModelModule::class
        ]
    )
    abstract fun contributeSplashFragment(): SplashFragment
}