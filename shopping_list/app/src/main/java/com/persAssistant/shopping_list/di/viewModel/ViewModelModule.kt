package com.persAssistant.shopping_list.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.persAssistant.shopping_list.presentation.util.NavigationViewModel
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
import com.persAssistant.shopping_list.presentation.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    fun bindModuleNavigationViewModel(NavigationViewModel: NavigationViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
