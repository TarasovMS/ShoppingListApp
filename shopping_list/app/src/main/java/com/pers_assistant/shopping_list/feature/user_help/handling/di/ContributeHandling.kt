package com.pers_assistant.shopping_list.feature.user_help.handling.di

import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.feature.user_help.handling.ui.HandlingFragment
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress(SUPPRESS_UNUSED)
@Module
abstract class ContributeHandling {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            HandlingVmModule::class,
        ]
    )
    abstract fun contributeHandlingFragment(): HandlingFragment

}
