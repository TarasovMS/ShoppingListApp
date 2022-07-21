package com.persAssistant.shopping_list.ui.fragment.shopping_list

import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.EditorShoppingListViewModel
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ShoppingListViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorShoppingListFragment : ShoppingListFragment() {

    @Inject
    lateinit var shoppingListViewModel: EditorShoppingListViewModel

    override fun createViewModel(): ShoppingListViewModel {
        val shoppingListId = arguments
            ?.getLong(SHOPPING_LIST_KEY)
            ?: throw Exception("Error in EditorShoppingListActivity.getIntent absent Id ")

        shoppingListViewModel.init(shoppingListId)

        return shoppingListViewModel
    }
}
