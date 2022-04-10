package com.tarasovms.billing.domain

import com.android.billingclient.api.SkuDetails

interface GetSubscriptionsUseCase {
    suspend fun getSubscriptions(): List<SkuDetails>?
}