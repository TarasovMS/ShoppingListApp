package com.persAssistant.shopping_list.feature.billing.ui

interface GetPurchasesUseCase {
    suspend fun getPurchasedSubscriptions(): List<Purchase>?
}