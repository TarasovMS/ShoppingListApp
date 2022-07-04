package com.persAssistant.shopping_list.ui.fragment.purchase

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import com.persAssistant.shopping_list.util.KEY_SHOPPING_LIST_ID
import java.lang.Exception
import javax.inject.Inject

class CreatorPurchaseFragment: PurchaseFragment() {

    val args: CreatorPurchaseFragmentArgs by navArgs()

    @Inject
    lateinit var purchaseViewModel: CreatorPurchaseViewModel

    override fun createViewModel(): PurchaseViewModel {
        val listId = arguments?.getLong(KEY_SHOPPING_LIST_ID)
            ?: throw Exception(getString(R.string.error_id_in_creator_purchase_activity))

//        purchaseViewModel.init(listId)
        purchaseViewModel.init(args.listId)

        return purchaseViewModel
    }
}
