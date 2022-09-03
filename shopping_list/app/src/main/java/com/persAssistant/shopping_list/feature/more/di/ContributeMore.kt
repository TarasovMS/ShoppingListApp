package com.persAssistant.shopping_list.feature.more.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.more.MoreFragment
import com.persAssistant.shopping_list.feature.more.di.MoreViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeMore {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            MoreViewModelModule::class
        ]
    )
    abstract fun contributeMoreFragment(): MoreFragment
}
