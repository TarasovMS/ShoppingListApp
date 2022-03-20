package com.persAssistant.shopping_list.di.contributes

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.user_help.handling.di.ContributeHandling
import com.persAssistant.shopping_list.ui.fragment.pref.PrefFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributePref {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            PrefViewModelModule::class
        ]
    )
    abstract fun contributePrefFragment(): PrefFragment
}