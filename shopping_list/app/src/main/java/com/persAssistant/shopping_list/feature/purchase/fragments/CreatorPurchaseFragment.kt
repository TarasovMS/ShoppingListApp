package com.persAssistant.shopping_list.feature.purchase.fragments

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.feature.purchase.view_model.CreatorPurchaseViewModel
import com.persAssistant.shopping_list.feature.purchase.view_model.PurchaseViewModel
import javax.inject.Inject

class CreatorPurchaseFragment: PurchaseFragment() {

    @Inject
    lateinit var purchaseViewModel: CreatorPurchaseViewModel
    private val args: CreatorPurchaseFragmentArgs by navArgs()

    override fun createViewModel(): PurchaseViewModel {
        purchaseViewModel.init(args.listId)
        return purchaseViewModel
    }

}
