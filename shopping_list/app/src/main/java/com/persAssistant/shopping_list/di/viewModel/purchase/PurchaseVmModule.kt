package com.persAssistant.shopping_list.di.viewModel.purchase

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface PurchaseVmModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(PurchaseViewModel::class)
    fun bindPurchaseViewModel(viewModel: PurchaseViewModel): ViewModel

}
