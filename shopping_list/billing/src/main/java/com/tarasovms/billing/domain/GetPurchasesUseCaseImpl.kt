package com.tarasovms.billing.domain

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.tarasovms.billing.data.PricingRepository

class GetPurchasesUseCaseImpl(
    private val pricingRepository: PricingRepository
) : GetPurchasesUseCase {

    override suspend fun getPurchasedSubscriptions(): List<Purchase>? = pricingRepository.getPurchases(
        BillingClient.SkuType.INAPP
    )
}