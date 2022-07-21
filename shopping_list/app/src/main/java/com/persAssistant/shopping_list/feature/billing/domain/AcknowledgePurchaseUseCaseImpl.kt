package com.persAssistant.shopping_list.feature.billing.domain

import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.Purchase.PurchaseState.PURCHASED
import com.persAssistant.shopping_list.feature.billing.data.PricingRepository

class AcknowledgePurchaseUseCaseImpl(
    private val pricingRepository: PricingRepository
) : AcknowledgePurchaseUseCase {

    override suspend fun acknowledgePurchase(purchase: Purchase): BillingResult? {
        if (purchase.purchaseState != PURCHASED || purchase.isAcknowledged)
            return null

        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        return pricingRepository.acknowledgePurchase(acknowledgePurchaseParams)
    }
}