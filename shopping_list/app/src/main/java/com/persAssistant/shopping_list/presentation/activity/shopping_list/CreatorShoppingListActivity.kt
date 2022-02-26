package com.persAssistant.shopping_list.presentation.activity.shopping_list

import com.persAssistant.shopping_list.presentation.App

class CreatorShoppingListActivity : ShoppingListActivity() {

    override fun createViewModel(): ShoppingListViewModel {
        val app = applicationContext as App
        return app.appComponent.getCreatorShoppingListViewModel()
    }
}