package com.pers_assistant.shopping_list.ui.fragment.shopping_list

import com.pers_assistant.shopping_list.ui.fragment.shopping_list.view_model.CreatorShoppingListViewModel
import com.pers_assistant.shopping_list.ui.fragment.shopping_list.view_model.ShoppingListViewModel
import javax.inject.Inject

class CreatorShoppingListFragment : ShoppingListFragment() {

    @Inject
    lateinit var shoppingListViewModel: CreatorShoppingListViewModel

    override fun createViewModel(): ShoppingListViewModel {
        return shoppingListViewModel
    }
}