package com.persAssistant.shopping_list.di.viewModel.vm

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.presentation.activity.shopping_list.ShoppingListViewModel
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface ShoppingListVmModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel::class)
    fun bindShoppingListViewModel(viewModel: ShoppingListViewModel): ViewModel

}