package com.pers_assistant.shopping_list.ui.fragment.purchase

import com.pers_assistant.shopping_list.ui.fragment.purchase.view_model.EditorPurchaseViewModel
import com.pers_assistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorPurchaseFragment: PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: EditorPurchaseViewModel

    override fun createViewModel(): PurchaseViewModel {
        val id = arguments
            ?.getLong(KEY_PURCHASE_ID)
            ?: throw Exception("Error in EditorPurchaseActivity.getIntent absent Id")

        purchaseViewModel.init(id)

        return purchaseViewModel
    }
}
