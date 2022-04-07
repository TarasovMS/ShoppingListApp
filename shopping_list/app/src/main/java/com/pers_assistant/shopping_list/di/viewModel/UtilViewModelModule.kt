package com.pers_assistant.shopping_list.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pers_assistant.shopping_list.util.NavigationViewModel
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import com.pers_assistant.shopping_list.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface UtilViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    fun bindModuleNavigationViewModel(NavigationViewModel: NavigationViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
