//package com.persAssistant.shopping_list.feature.billing.domain
//
//import com.android.billingclient.api.BillingClient
//import com.android.billingclient.api.Purchase
//import com.persAssistant.shopping_list.feature.billing.data.PricingRepository
//
//class GetPurchasesUseCaseImpl(
//    private val pricingRepository: PricingRepository
//) : GetPurchasesUseCase {
//
//    override suspend fun getPurchasedSubscriptions(): List<Purchase>? = pricingRepository.getPurchases(
//        BillingClient.SkuType.INAPP
//    )
//}
