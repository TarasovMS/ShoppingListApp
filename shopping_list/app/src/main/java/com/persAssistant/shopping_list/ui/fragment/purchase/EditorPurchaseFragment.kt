package com.persAssistant.shopping_list.ui.fragment.purchase

import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.EditorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.KEY_PURCHASE_ID
import java.lang.Exception
import javax.inject.Inject

class EditorPurchaseFragment : PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: EditorPurchaseViewModel

    override fun createViewModel(): PurchaseViewModel {
        val id = arguments?.getLong(KEY_PURCHASE_ID)
            ?: throw Exception(getString(R.string.error_id_in_editor_purchase_activity))

        purchaseViewModel.init(id)

        return purchaseViewModel
    }
}
