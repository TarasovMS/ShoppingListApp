package com.persAssistant.shopping_list.feature.billing.ui

class PricingRepositoryImpl(
    billingClientProvider: BillingClientProvider
) : PricingRepository {

    private val billingClient = billingClientProvider.billingClient

    override suspend fun getPurchases(purchaseType: String): List<Purchase>? {
        val connectIfNeeded = connectIfNeeded()
        if (!connectIfNeeded)
            return null

        return billingClient.queryPurchases(purchaseType).purchasesList
    }

    private suspend fun connectIfNeeded(): Boolean {
        return billingClient.isReady || billingClient.connect()
    }
}