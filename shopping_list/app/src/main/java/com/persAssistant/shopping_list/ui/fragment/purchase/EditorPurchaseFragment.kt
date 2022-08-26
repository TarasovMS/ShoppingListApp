package com.persAssistant.shopping_list.ui.fragment.purchase

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.EditorPurchaseViewModel
import com.persAssistant.shopping_list.ui.fragment.purchase.view_model.PurchaseViewModel
import javax.inject.Inject

class EditorPurchaseFragment : PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: EditorPurchaseViewModel

    private val args: EditorPurchaseFragmentArgs by navArgs()

    override fun createViewModel(): PurchaseViewModel {
        purchaseViewModel.initPurchase(args.purchaseId)
        return purchaseViewModel
    }

}
