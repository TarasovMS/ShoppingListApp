package com.persAssistant.shopping_list.di.viewModel

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.presentation.fragment.list_of_shopping_list_fragment.ListOfShoppingListViewModel
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
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
