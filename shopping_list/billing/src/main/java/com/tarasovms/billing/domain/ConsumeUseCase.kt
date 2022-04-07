package com.tarasovms.billing.domain

import com.android.billingclient.api.Purchase

interface ConsumeUseCase {
    suspend fun consumePurchase(purchase: Purchase)
}