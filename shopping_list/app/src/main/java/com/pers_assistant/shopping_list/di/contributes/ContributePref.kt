package com.pers_assistant.shopping_list.di.contributes

import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.ui.fragment.pref.PrefFragment
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