package com.persAssistant.shopping_list.feature.user_help.handling.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.user_help.handling.ui.HandlingFragment
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@[Module Suppress(SUPPRESS_UNUSED)]
abstract class ContributeHandling {

    @[FragmentScope ContributesAndroidInjector(
        modules = [
            HandlingVmModule::class,
        ]
    )]
    abstract fun contributeHandlingFragment(): HandlingFragment

}
