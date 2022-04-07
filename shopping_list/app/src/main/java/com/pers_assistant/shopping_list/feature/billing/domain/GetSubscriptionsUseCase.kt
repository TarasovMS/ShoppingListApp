package com.pers_assistant.shopping_list.feature.billing.domain

import com.android.billingclient.api.SkuDetails

interface GetSubscriptionsUseCase {
    suspend fun getSubscriptions(): List<SkuDetails>?
}