package com.pers_assistant.shopping_list.di.viewModel.purchase

import androidx.lifecycle.ViewModel
import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.di.viewModel.ViewModelKey
import com.pers_assistant.shopping_list.ui.fragment.purchase.view_model.ListOfPurchaseViewModel
import com.pers_assistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface ListOfPurchaseVmModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(ListOfPurchaseViewModel::class)
    fun bindListOfPurchaseViewModel(viewModel: ListOfPurchaseViewModel): ViewModel

}
