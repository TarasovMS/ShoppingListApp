package com.persAssistant.shopping_list.feature.billing.data

import com.android.billingclient.api.*
import com.persAssistant.shopping_list.feature.billing.util.BillingClientProvider
import com.persAssistant.shopping_list.feature.billing.util.ConsumeProductResult

class PricingRepositoryImpl(
    billingClientProvider: BillingClientProvider
) : PricingRepository {

    private val billingClient = billingClientProvider.billingClient

    /**
     * Получаем покупки
     */
    override suspend fun getPurchases(purchaseType: String): List<Purchase>? {
        val connectIfNeeded = connectIfNeeded()
        if (!connectIfNeeded)
            return null

//        return billingClient.queryPurchases(purchaseType).purchasesList
        return listOf()
    }

    /**
     * Получаем продукты
     */
    override suspend fun getProducts(productDetailsParams: SkuDetailsParams): List<SkuDetails>? {
        TODO("Not yet implemented")
    }

    /**
     * Предоставляем доступ
     */
    override suspend fun consumeProduct(consumeParams: ConsumeParams): ConsumeProductResult {
        TODO("Not yet implemented")
    }

    /**
     * Подтвержаем покупку
     */
    override suspend fun acknowledgePurchase(acknowledgePurchaseParams: AcknowledgePurchaseParams): BillingResult {
        TODO("Not yet implemented")
    }

    /**
     * Получаем клиента
     */
    override fun getBillingClient(): BillingClient {
        TODO("Not yet implemented")
    }

    /**
     * Подключаем при необходимости
     */
    private suspend fun connectIfNeeded(): Boolean {
        val billingClientConnection = billingClient.startConnection(
            object : BillingClientStateListener {

                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        // The BillingClient is ready. You can query purchases here.

                        // BillingClient готов. Вы можете запросить покупки здесь.
                    }
                }

                override fun onBillingServiceDisconnected() {
                    // Try to restart the connection on the next request to
                    // Google Play by calling the startConnection() method.

                    // Пытаемся перезапустить соединение при следующем запросе к
                    // Google Play, вызвав метод startConnection().

                }

            })

//        return billingClient.isReady || billingClient.connect()
        return billingClient.isReady
    }
}
