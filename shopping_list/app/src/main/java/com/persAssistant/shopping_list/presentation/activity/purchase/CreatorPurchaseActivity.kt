package com.persAssistant.shopping_list.presentation.activity.purchase

import com.persAssistant.shopping_list.presentation.App
import java.lang.Exception

class CreatorPurchaseActivity: PurchaseActivity() {

    override fun createViewModel(): PurchaseViewModel {
        val app = applicationContext as App
        val listId = intent.getLongExtra(KEY_SHOPPING_LIST_ID,-1L)
        if (listId == -1L)
            throw Exception("Ошибка в PurchaseActivity отсутствует listId")

        val viewModel = app.appComponent.getCreatorPurchaseViewModel()
        viewModel.init(listId)
        return viewModel
    }
}
