package com.persAssistant.shopping_list.presentation.activity.purchase.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.purchase.PurchaseViewModel
import java.lang.Exception

class CreatorPurchaseFragment: PurchaseFragment() {

    override fun createViewModel(): PurchaseViewModel {
        val app = requireContext().applicationContext as App
        val listId = arguments?.getLong(KEY_SHOPPING_LIST_ID)
        if (listId == null)
            throw Exception("Ошибка в PurchaseActivity отсутствует listId")

        val viewModel = app.appComponent.getCreatorPurchaseViewModel()
        viewModel.init(listId)
        return viewModel
    }
}
