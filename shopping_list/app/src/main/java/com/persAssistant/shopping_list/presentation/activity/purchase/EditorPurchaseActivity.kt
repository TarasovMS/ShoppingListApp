package com.persAssistant.shopping_list.presentation.activity.purchase

import com.persAssistant.shopping_list.presentation.App
import java.lang.Exception

class EditorPurchaseActivity: PurchaseActivity() {

    override fun createViewModel(): PurchaseViewModel {
        val app = applicationContext as App
        val id = intent.getLongExtra(KEY_PURCHASE_ID,-1L)
        if(id == -1L)
            throw Exception("Ошибка в EditorPurchaseActivity.getIntent отсутствует Id")

        val viewModel = app.appComponent.getEditorPurchaseViewModel()
        viewModel.init(id)
        return viewModel
    }
}
