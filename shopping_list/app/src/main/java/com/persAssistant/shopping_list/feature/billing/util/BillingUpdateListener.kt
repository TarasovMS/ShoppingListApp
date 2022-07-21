//package com.persAssistant.shopping_list.feature.billing.util
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.android.billingclient.api.BillingResult
//import com.android.billingclient.api.Purchase
//import com.android.billingclient.api.PurchasesUpdatedListener
//import javax.inject.Inject
//
//class BillingUpdateListener @Inject constructor() : PurchasesUpdatedListener {
//
//    private val _purchaseUpdateLiveData = MutableLiveData<List<Purchase>?>()
//    val purchaseUpdateLiveData: LiveData<List<Purchase>?> = _purchaseUpdateLiveData
//
//    override fun onPurchasesUpdated(
//        billingResult: BillingResult,
//        purchases: MutableList<Purchase>?
//    ) {
//        _purchaseUpdateLiveData.value = purchases
//    }
//}
