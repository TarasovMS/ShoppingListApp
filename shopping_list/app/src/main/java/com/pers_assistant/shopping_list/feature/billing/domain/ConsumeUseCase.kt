package com.pers_assistant.shopping_list.feature.billing.domain

import com.android.billingclient.api.Purchase

interface ConsumeUseCase {
    suspend fun consumePurchase(purchase: Purchase)
}