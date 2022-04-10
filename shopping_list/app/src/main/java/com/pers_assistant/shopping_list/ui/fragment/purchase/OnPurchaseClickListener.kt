package com.pers_assistant.shopping_list.ui.fragment.purchase

import com.pers_assistant.shopping_list.domain.entities.Purchase

interface OnPurchaseClickListener {
    fun clickedPurchaseItem(purchase: Purchase)
    fun deleteItem(purchase: Purchase)
    fun editItem(purchase: Purchase)
}