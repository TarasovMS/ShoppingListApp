package com.persAssistant.shopping_list.presentation.activity.purchase.fragment

import com.persAssistant.shopping_list.presentation.activity.purchase.EditorPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.purchase.PurchaseViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorPurchaseFragment: PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: EditorPurchaseViewModel

    override fun createViewModel(): PurchaseViewModel {
        val id = arguments
            ?.getLong(KEY_PURCHASE_ID)
            ?: throw Exception("Ошибка в EditorPurchaseActivity.getIntent отсутствует Id")

        purchaseViewModel.init(id)
        return purchaseViewModel
    }
}
