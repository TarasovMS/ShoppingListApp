package com.persAssistant.shopping_list.feature.billing.di

import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.feature.billing.ui.BillingFragment
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
    abstract fun contributeBillingFragment(): BillingFragment
}