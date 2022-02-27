package com.persAssistant.shopping_list.presentation.activity.shopping_list.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.shopping_list.ShoppingListViewModel
import java.lang.Exception

class EditorShoppingListFragment : ShoppingListFragment() {

    override fun createViewModel(): ShoppingListViewModel {
        val app = requireContext().applicationContext as App
        val shoppingListId = arguments?.getLong(SHOPPING_LIST_KEY)
        if(shoppingListId == null)
            throw Exception("Ошибка в EditorShoppingListActivity.getIntent отсутствует Id ")

        val viewModel = app.appComponent.getEditorShoppingListViewModel()
        viewModel.init(shoppingListId)
        return viewModel
    }
}