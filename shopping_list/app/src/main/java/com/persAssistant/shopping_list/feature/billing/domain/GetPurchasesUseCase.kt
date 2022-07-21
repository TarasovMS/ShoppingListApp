package com.persAssistant.shopping_list.feature.billing.domain

import com.android.billingclient.api.Purchase

interface GetPurchasesUseCase {
    suspend fun getPurchasedSubscriptions(): List<Purchase>?
}