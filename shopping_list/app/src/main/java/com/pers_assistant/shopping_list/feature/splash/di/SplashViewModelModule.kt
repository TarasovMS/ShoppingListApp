package com.pers_assistant.shopping_list.feature.splash.di

import androidx.lifecycle.ViewModel
import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.di.viewModel.ViewModelKey
import com.pers_assistant.shopping_list.feature.splash.ui.SplashViewModel
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface SplashViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

}
