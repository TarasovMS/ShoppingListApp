package com.tarasovms.billing.domain

import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.tarasovms.billing.data.PricingRepository

class ConsumeUseCaseImpl(
    private val pricingRepository: PricingRepository
) : ConsumeUseCase {

    override suspend fun consumePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        pricingRepository.consumeProduct(consumeParams)
    }
}