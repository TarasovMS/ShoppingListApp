package com.tarasovms.billing.domain

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase

interface AcknowledgePurchaseUseCase {
    suspend fun acknowledgePurchase(purchase: Purchase): BillingResult?
}