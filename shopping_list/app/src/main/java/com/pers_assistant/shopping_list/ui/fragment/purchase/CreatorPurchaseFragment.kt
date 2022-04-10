package com.pers_assistant.shopping_list.ui.fragment.purchase

import com.pers_assistant.shopping_list.ui.fragment.purchase.view_model.CreatorPurchaseViewModel
import com.pers_assistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
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
