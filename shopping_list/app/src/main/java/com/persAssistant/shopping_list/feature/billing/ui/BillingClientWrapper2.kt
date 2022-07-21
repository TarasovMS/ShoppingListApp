package com.persAssistant.shopping_list.feature.billing.ui

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*

class BillingClientWrapper2(context: Context) : PurchasesUpdatedListener {

    interface OnQueryProductsListener {
        fun onSuccess(products: List<SkuDetails>)
        fun onFailure(error: Error)
    }

    interface OnPurchaseListener {
        fun onPurchaseSuccess(purchase: Purchase?)
        fun onPurchaseFailure(error: Error)
    }

    interface OnQueryActivePurchasesListener {
        fun onSuccess(activePurchases: List<Purchase>)
        fun onFailure(error: Error)
    }

    interface OnQueryPurchaseHistoryListener {
        fun onSuccess(purchaseHistoryList: List<PurchaseHistoryRecord>)
        fun onFailure(error: Error)
    }

    var onPurchaseListener: OnPurchaseListener? = null

    private val billingClient = BillingClient
        .newBuilder(context)
        .enablePendingPurchases()
        .setListener(this)
        .build()

    /**
     * Здесь колбэк возвращает список с самой последней покупкой каждого продукта,
     * который пользователь когда-либо приобретал,
     * а заодно синхронизирует локальный кэш активных покупок с актуальным состоянием.
     * Также обратите внимание, что покупка здесь представлена не классом Purchase,
     * а классом PurchaseHistoryRecord.
     */
    private fun queryPurchasesHistoryForType(
        @BillingClient.SkuType type: String,
        listener: PurchaseHistoryResponseListener
    ) {
        onConnected {
            billingClient.queryPurchaseHistoryAsync(type, listener)
        }
    }

    fun queryPurchaseHistory(listener: OnQueryPurchaseHistoryListener) {
        queryPurchasesHistoryForType(
            BillingClient.SkuType.SUBS
        ) { billingResult, subsHistoryList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                queryPurchasesHistoryForType(
                    BillingClient.SkuType.INAPP
                ) { billingResult, inappHistoryList ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        listener.onSuccess(
                            subsHistoryList?.apply { addAll(inappHistoryList.orEmpty()) }.orEmpty()
                        )
                    } else {
                        listener.onFailure(
                            Error(billingResult.responseCode, billingResult.debugMessage)
                        )
                    }
                }
            } else {
                listener.onFailure(
                    Error(billingResult.responseCode, billingResult.debugMessage)
                )
            }
        }
    }

    /**
     * если пользователь приобретет обе подписки, используя метод,
     * который мы описали в предыдущем руководстве, обе будут активированы.
     * Это не всегда ожидаемое поведение.
     */

    fun querySyncedActivePurchases(listener: OnQueryActivePurchasesListener) {
        queryPurchaseHistory(object : OnQueryPurchaseHistoryListener {
            override fun onSuccess(purchaseHistoryList: List<PurchaseHistoryRecord>) {
                queryActivePurchases(listener)
            }

            override fun onFailure(error: Error) {
                listener.onFailure(error)
            }
        })
    }

    private fun queryActivePurchasesForType(
        @BillingClient.SkuType type: String,
        listener: PurchasesResponseListener
    ) {
        onConnected {
            billingClient.queryPurchasesAsync(type, listener)
        }
    }

    fun queryActivePurchases(listener: OnQueryActivePurchasesListener) {
        queryActivePurchasesForType(
            BillingClient.SkuType.SUBS
        ) { billingResult, activeSubsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                queryActivePurchasesForType(
                    BillingClient.SkuType.INAPP
                ) { billingResult, nonConsumableProductsList ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        listener.onSuccess(
                            activeSubsList.apply { addAll(nonConsumableProductsList) }
                        )
                    } else {
                        listener.onFailure(
                            Error(billingResult.responseCode, billingResult.debugMessage)
                        )
                    }
                }
            } else {
                listener.onFailure(
                    Error(billingResult.responseCode, billingResult.debugMessage)
                )
            }
        }
    }


    fun purchase(activity: Activity, product: SkuDetails) {
        onConnected {
            activity.runOnUiThread {
                billingClient.launchBillingFlow(
                    activity,
                    BillingFlowParams.newBuilder().setSkuDetails(product).build()
                )
            }
        }
    }

    /**
     * метод, который «склеит» результаты от двух запросов и вернет итоговый список продуктов
     * или ошибку.
     */
    fun queryProducts(listener: OnQueryProductsListener) {
        val skusList = listOf("premium_sub_month", "premium_sub_year", "coin_pack_large", "unlock_feature")

        queryProductsForType(
            skusList,
            BillingClient.SkuType.SUBS
        ) { billingResult, skuDetailsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val products = skuDetailsList ?: mutableListOf()
                queryProductsForType(
                    skusList,
                    BillingClient.SkuType.INAPP
                ) { billingResult, skuDetailsList ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        products.addAll(skuDetailsList ?: listOf())
                        listener.onSuccess(products)
                    } else {
                        listener.onFailure(
                            Error(billingResult.responseCode, billingResult.debugMessage)
                        )
                    }
                }
            } else {
                listener.onFailure(
                    Error(billingResult.responseCode, billingResult.debugMessage)
                )
            }
        }
    }

    /** подписки различаются только своим расчетным периодом, поэтому было бы разумно,
     *  чтобы они были взаимоисключающими.
     *  Давайте улучшим наш класс BillingClientWrapper с помощью метода замены старой подписки на новую
     */
    fun changeSubscription(
        activity: Activity,
        newSub: SkuDetails,
        updateParams: BillingFlowParams.SubscriptionUpdateParams
    ) {
        onConnected {
            activity.runOnUiThread {
                billingClient.launchBillingFlow(
                    activity,
                    BillingFlowParams.newBuilder().setSkuDetails(newSub)
                        .setSubscriptionUpdateParams(updateParams).build()
                )
            }
        }
    }

    // метод для получения данных о продуктах конкретного типа
    private fun queryProductsForType(
        skusList: List<String>,
        @BillingClient.SkuType type: String,
        listener: SkuDetailsResponseListener
    ) {
        onConnected {
            billingClient.querySkuDetailsAsync(
                SkuDetailsParams.newBuilder().setSkusList(skusList).setType(type).build(),
                listener
            )
        }
    }

    private fun onConnected(block: () -> Unit) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                block.invoke()
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    /**
     * Метод вызывается после совершения покупки
     */
    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchaseList: MutableList<Purchase>?
    ) {
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                if (purchaseList == null) {
                    //to be discussed in the next article
                    onPurchaseListener?.onPurchaseSuccess(null)
                    return
                }

                purchaseList.forEach(::processPurchase)
            }
            else -> {
                //error occured or user canceled
                onPurchaseListener?.onPurchaseFailure(
                    BillingClientWrapper2.Error(
                        billingResult.responseCode,
                        billingResult.debugMessage
                    )
                )
            }
        }
    }

    private fun processPurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            onPurchaseListener?.onPurchaseSuccess(purchase)

            if (purchase.skus.firstOrNull() == "coin_pack_large") {
                //consuming our only consumable product
                consumePurchase(purchase) { billingResult, purchaseToken ->
                    if (billingResult.responseCode != BillingClient.BillingResponseCode.OK) {
                        //implement retry logic or try to consume again in onResume()
                    }
                }
            } else if (!purchase.isAcknowledged) {
                acknowledgePurchase(purchase) { billingResult ->
                    if (billingResult.responseCode != BillingClient.BillingResponseCode.OK) {
                        //implement retry logic or try to acknowledge again in onResume()
                    }
                }
            }
        }
    }

    private fun acknowledgePurchase(
        purchase: Purchase,
        callback: AcknowledgePurchaseResponseListener
    ) {
        onConnected {
            billingClient.acknowledgePurchase(
                AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken)
                    .build(),
                callback::onAcknowledgePurchaseResponse
            )
        }
    }

    private fun consumePurchase(purchase: Purchase, callback: ConsumeResponseListener) {
        onConnected {
            billingClient.consumeAsync(
                ConsumeParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
            ) { billingResult, purchaseToken ->
                callback.onConsumeResponse(billingResult, purchaseToken)
            }
        }
    }

    class Error(val responseCode: Int, val debugMessage: String)

}
