package com.persAssistant.shopping_list.feature.billing.ui

interface PricingRepository {
    suspend fun getPurchases(purchaseType: String): List<Purchase>?
}