package com.persAssistant.shopping_list.feature.billing.data

import com.android.billingclient.api.*
import com.persAssistant.shopping_list.feature.billing.util.ConsumeProductResult

//TODO
interface PricingRepository {
    suspend fun getPurchases(purchaseType: String): List<Purchase>?
    suspend fun getProducts(productDetailsParams: SkuDetailsParams): List<SkuDetails>?
    suspend fun consumeProduct(consumeParams: ConsumeParams): ConsumeProductResult
    suspend fun acknowledgePurchase(acknowledgePurchaseParams: AcknowledgePurchaseParams): BillingResult
    fun getBillingClient(): BillingClient
}
