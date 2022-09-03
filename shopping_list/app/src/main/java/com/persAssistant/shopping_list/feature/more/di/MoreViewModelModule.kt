package com.persAssistant.shopping_list.feature.more.di

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.more.MoreViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface MoreViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    fun bindMoreViewModel(viewModel: MoreViewModel): ViewModel

}
