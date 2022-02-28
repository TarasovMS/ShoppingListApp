package com.persAssistant.shopping_list.presentation.activity.purchase.fragment

import com.persAssistant.shopping_list.presentation.activity.purchase.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.presentation.activity.purchase.PurchaseViewModel
import java.lang.Exception
import javax.inject.Inject

class CreatorPurchaseFragment: PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: CreatorPurchaseViewModel

    override fun createViewModel(): PurchaseViewModel {
        val listId = arguments
            ?.getLong(KEY_SHOPPING_LIST_ID)
            ?: throw Exception("Error in PurchaseActivity absent listId")

        purchaseViewModel.init(listId)
        return purchaseViewModel
    }
}
