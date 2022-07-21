package com.persAssistant.shopping_list.feature.billing.util

import com.android.billingclient.api.*
import kotlinx.coroutines.suspendCancellableCoroutine


suspend fun BillingClient.connect(): Boolean {

    return suspendCancellableCoroutine { continuation ->
        startConnection(object : BillingClientStateListener {

            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    continuation.resume(
                        true,
                        throw Exception("ошибка в onBillingSetupFinished -> billingResult.responseCode")
                    )
                } else {
                    continuation.resume(
                        false,
                        throw Exception("ошибка в onBillingSetupFinished -> else ")
                    )
                }
            }

            override fun onBillingServiceDisconnected() {
                continuation.resume(
                    false,
                    throw Exception("ошибка в onBillingServiceDisconnected ")
                )
            }
        })
    }
}

suspend fun BillingClient.getProducts(params: SkuDetailsParams): List<SkuDetails>? {
    return suspendCancellableCoroutine { continuation ->
        querySkuDetailsAsync(params) { _, products ->
            continuation.resume(
                products,
                throw Exception("ошибка в BillingClient.getProducts ")
            )
        }
    }
}

suspend fun BillingClient.consumeProduct(consumeParams: ConsumeParams): ConsumeProductResult {
    return suspendCancellableCoroutine { continuation ->
        consumeAsync(consumeParams) { billingResult, purchaseToken ->
            continuation.resume(
                ConsumeProductResult(
                    billingResult = billingResult,
                    purchaseToken = purchaseToken
                ),
                throw Exception("ошибка в BillingClient.consumeProduct ")
            )
        }
    }
}
