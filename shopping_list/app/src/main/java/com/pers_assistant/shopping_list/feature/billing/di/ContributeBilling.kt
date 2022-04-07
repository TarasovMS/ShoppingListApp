package com.pers_assistant.shopping_list.feature.billing.di

import com.pers_assistant.shopping_list.di.scopes.FragmentScope
import com.pers_assistant.shopping_list.feature.billing.ui.PricingFragment
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