package com.persAssistant.shopping_list.feature.billing.di

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.billing.ui.PricingViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface BillingViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(PricingViewModel::class)
    fun bindSplashViewModel(viewModel: PricingViewModel): ViewModel

}
