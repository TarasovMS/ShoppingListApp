package com.persAssistant.shopping_list.feature.connection_unavailable.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.connection_unavailable.ConnectionUnavailableFragment
import com.persAssistant.shopping_list.feature.language.ui.LanguageFragment
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress(SUPPRESS_UNUSED)
@Module
abstract class ContributeConnection {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ConnectionVmModule::class,
        ]
    )
    abstract fun contributeConnectionFragment(): ConnectionUnavailableFragment

}
