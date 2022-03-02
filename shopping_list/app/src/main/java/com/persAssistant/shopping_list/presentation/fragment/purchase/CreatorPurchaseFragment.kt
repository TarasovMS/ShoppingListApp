package com.persAssistant.shopping_list.presentation.fragment.purchase

import com.persAssistant.shopping_list.presentation.fragment.purchase.view_model.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.presentation.fragment.purchase.view_model.PurchaseViewModel
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
