package com.persAssistant.shopping_list.ui.fragment.shopping_list

import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.EditorShoppingListViewModel
import com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model.ShoppingListViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorShoppingListFragment : ShoppingListFragment() {

    @Inject
    lateinit var shoppingListViewModel: EditorShoppingListViewModel

    override fun createViewModel(): ShoppingListViewModel {
        val shoppingListId = arguments?.getLong(SHOPPING_LIST_KEY)
            ?: throw Exception(getString(R.string.error_id_in_editor_shopplist_activity))

        shoppingListViewModel.init(shoppingListId)

        return shoppingListViewModel
    }
}
