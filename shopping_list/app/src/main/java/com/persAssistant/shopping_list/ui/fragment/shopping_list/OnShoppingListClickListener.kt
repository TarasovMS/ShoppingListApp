package com.persAssistant.shopping_list.ui.fragment.shopping_list

import com.persAssistant.shopping_list.domain.entities.ShoppingList

interface OnShoppingListClickListener {
    fun clickedShoppingListItem(shoppingList: ShoppingList)
    fun deleteItem(shoppingList: ShoppingList)
    fun editItem(shoppingList: ShoppingList)
}