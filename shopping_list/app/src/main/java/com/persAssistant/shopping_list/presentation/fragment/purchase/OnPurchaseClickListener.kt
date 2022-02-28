package com.persAssistant.shopping_list.presentation.fragment.purchase

import com.persAssistant.shopping_list.domain.entities.Purchase

interface OnPurchaseClickListener {
    fun clickedPurchaseItem(purchase: Purchase)
    fun deleteItem(purchase: Purchase)
    fun editItem(purchase: Purchase)
}