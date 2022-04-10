package com.tarasovms.billing.domain

import com.android.billingclient.api.Purchase

interface GetPurchasesUseCase {
    suspend fun getPurchasedSubscriptions(): List<Purchase>?
}