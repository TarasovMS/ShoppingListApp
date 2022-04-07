package com.tarasovms.billing.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.tarasovms.billing.ui.PricingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeBilling {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            BillingViewModelModule::class
        ]
    )
    abstract fun contributeBillingFragment(): PricingFragment
}