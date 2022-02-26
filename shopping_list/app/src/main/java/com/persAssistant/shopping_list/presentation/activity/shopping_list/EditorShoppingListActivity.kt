package com.persAssistant.shopping_list.presentation.activity.shopping_list

import com.persAssistant.shopping_list.presentation.App
import java.lang.Exception

class EditorShoppingListActivity : ShoppingListActivity() {

    override fun createViewModel(): ShoppingListViewModel {
        val app = applicationContext as App
        val shoppingListId = intent.getLongExtra(SHOPPING_LIST_KEY,-1L)
        if(shoppingListId == -1L)
            throw Exception("Ошибка в EditorShoppingListActivity.getIntent отсутствует Id ")

        val viewModel = app.appComponent.getEditorShoppingListViewModel()
        viewModel.init(shoppingListId)
        return viewModel
    }
}