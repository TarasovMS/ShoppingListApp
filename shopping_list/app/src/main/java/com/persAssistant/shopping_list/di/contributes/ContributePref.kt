package com.persAssistant.shopping_list.di.contributes

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.ui.fragment.more.MoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributePref {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            MoreViewModelModule::class
        ]
    )
    abstract fun contributePrefFragment(): MoreFragment
}