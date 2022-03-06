package com.persAssistant.shopping_list.feature.billing.ui

import com.android.billingclient.api.BillingClient

class GetPurchasesUseCaseImpl(
    private val pricingRepository: PricingRepository
) : GetPurchasesUseCase {

    override suspend fun getPurchasedSubscriptions(): List<Purchase>? = pricingRepository.getPurchases(
        BillingClient.SkuType.SUBS
    )
}