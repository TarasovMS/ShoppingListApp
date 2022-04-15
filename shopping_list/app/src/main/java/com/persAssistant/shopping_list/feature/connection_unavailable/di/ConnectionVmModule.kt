package com.persAssistant.shopping_list.feature.connection_unavailable.di

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.connection_unavailable.ConnectionUnavailableViewModel
import com.persAssistant.shopping_list.feature.language.viewmodel.LanguageViewModel
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface ConnectionVmModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ConnectionUnavailableViewModel::class)
    fun bindLanguageViewModel(connectionUnavailableViewModel: ConnectionUnavailableViewModel): ViewModel

}
