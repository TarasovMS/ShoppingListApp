package com.persAssistant.shopping_list.feature.language.di

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.language.viewmodel.LanguageViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface LanguageVmModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(LanguageViewModel::class)
    fun bindLanguageViewModel(languageViewModel: LanguageViewModel): ViewModel

}
