package com.persAssistant.shopping_list.feature.billing.util

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.PurchasesUpdatedListener

class BillingClientProvider(
    context: Context,
    updateListener: PurchasesUpdatedListener
) {
    val billingClient = BillingClient
        .newBuilder(context)
        .enablePendingPurchases()
        .setListener(updateListener)
        .build()
}