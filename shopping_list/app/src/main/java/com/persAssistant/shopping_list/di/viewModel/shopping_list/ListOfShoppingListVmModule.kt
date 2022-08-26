package com.persAssistant.shopping_list.di.viewModel.shopping_list

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface ListOfShoppingListVmModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(ListOfShoppingListViewModel::class)
    fun bindListOfShoppingListViewModel(viewModel: ListOfShoppingListViewModel): ViewModel

}
