package com.pers_assistant.shopping_list.feature.user_help.handling.di

import androidx.lifecycle.ViewModel
import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.di.viewModel.ViewModelKey
import com.pers_assistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface HandlingVmModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(HandlingViewModel::class)
    fun bindWriteMessageViewModel(handlingViewModel: HandlingViewModel): ViewModel

}
