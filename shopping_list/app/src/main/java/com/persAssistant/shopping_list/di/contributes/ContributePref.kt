package com.persAssistant.shopping_list.di.contributes

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.ui.fragment.pref.PrefFragment
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress(SUPPRESS_UNUSED)
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
