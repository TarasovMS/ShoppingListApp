package com.persAssistant.shopping_list.presentation.activity.purchase.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.purchase.PurchaseViewModel
import java.lang.Exception

class EditorPurchaseFragment: PurchaseFragment() {

    override fun createViewModel(): PurchaseViewModel {
        val app = requireContext().applicationContext as App
        val id = arguments?.getLong(KEY_PURCHASE_ID)
        if(id == null)
            throw Exception("Ошибка в EditorPurchaseActivity.getIntent отсутствует Id")

        val viewModel = app.appComponent.getEditorPurchaseViewModel()
        viewModel.init(id)
        return viewModel
    }
}
