package com.persAssistant.shopping_list.di.contributes

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.ui.fragment.pref.PrefViewModel
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface PrefViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(PrefViewModel::class)
    fun bindPrefViewModel(viewModel: PrefViewModel): ViewModel

}
