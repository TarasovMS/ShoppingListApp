package com.tarasovms.billing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.persAssistant.shopping_list.base.AppBaseViewModel
import com.tarasovms.billing.domain.AcknowledgePurchaseUseCase
import com.tarasovms.billing.domain.ConsumeUseCase
import com.tarasovms.billing.domain.GetPurchasesUseCase
import com.tarasovms.billing.domain.GetSubscriptionsUseCase
import com.tarasovms.billing.util.BillingUpdateListener
import kotlinx.coroutines.launch

class PricingViewModel(
    private val consumeUseCase: ConsumeUseCase,
    private val acknowledgePurchaseUseCase: AcknowledgePurchaseUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase,
    billingUpdateListener: BillingUpdateListener
): AppBaseViewModel() {

    /**
     * Существующие покупки и подписки,
     * которые пользователь уже приобрел
     */
    private val _purchasedSubscriptionsLiveData = MutableLiveData<List<Purchase>?>()
    val purchasedSubscriptionsLiveData: LiveData<List<Purchase>?> = _purchasedSubscriptionsLiveData

    /**
     * Подписки
     */
    private val _subscriptionsLiveData = MutableLiveData<List<SkuDetails>?>()
    val subscriptionsLiveData: LiveData<List<SkuDetails>?> = _subscriptionsLiveData

    /**
     * Обновляем покупки
     * подтвердженные или нет?
     */
    val purchasesUpdateLiveData: LiveData<List<Purchase>?> = billingUpdateListener.purchaseUpdateLiveData

    init {
        viewModelScope.launch {
            _purchasedSubscriptionsLiveData.value = getPurchasesUseCase.getPurchasedSubscriptions()
        }
    }

    /**
     * Получаем Подписки
     */
    fun getSubscriptions() {
        viewModelScope.launch {
            val subscriptions = getSubscriptionsUseCase.getSubscriptions()
            _subscriptionsLiveData.value = subscriptions
        }
    }

    /**
     * Подтверджаем покупки
     */
    fun acknowledgePurchase(purchase: Purchase) {
        viewModelScope.launch {
            acknowledgePurchaseUseCase.acknowledgePurchase(purchase)
        }
    }

    /**
     * Потреблять продукт.
     * как понимаю это предоставление доступа для функций
     */
    fun consumeProduct(purchase: Purchase) {
        viewModelScope.launch {
            consumeUseCase.consumePurchase(purchase)
        }
    }

    /**
     * Получаем клиента
     */
//    fun getBillingClient(): BillingClient {
//        return  BillingClient
//    }


}