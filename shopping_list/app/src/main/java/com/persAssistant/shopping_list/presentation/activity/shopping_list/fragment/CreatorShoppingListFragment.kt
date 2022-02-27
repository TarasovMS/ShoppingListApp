package com.persAssistant.shopping_list.presentation.activity.shopping_list.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.shopping_list.ShoppingListViewModel

class CreatorShoppingListFragment : ShoppingListFragment() {

    override fun createViewModel(): ShoppingListViewModel {
        val app = requireContext().applicationContext as App
        return app.appComponent.getCreatorShoppingListViewModel()
    }
}