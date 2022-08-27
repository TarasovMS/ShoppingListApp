package com.persAssistant.shopping_list.feature.purchase.fragments

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.feature.purchase.view_model.EditorPurchaseViewModel
import com.persAssistant.shopping_list.feature.purchase.view_model.PurchaseViewModel
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
