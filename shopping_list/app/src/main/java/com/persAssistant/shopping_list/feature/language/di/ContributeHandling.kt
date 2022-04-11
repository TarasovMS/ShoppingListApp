package com.persAssistant.shopping_list.feature.language.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.language.ui.LanguageFragment
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress(SUPPRESS_UNUSED)
@Module
abstract class ContributeLanguage {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            LanguageVmModule::class,
        ]
    )
    abstract fun contributeLanguageFragment(): LanguageFragment

}
