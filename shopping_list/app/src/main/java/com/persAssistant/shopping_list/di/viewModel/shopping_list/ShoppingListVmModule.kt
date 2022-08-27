package com.persAssistant.shopping_list.di.viewModel.shopping_list

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.shopping_list.view_model.ShoppingListViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
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
