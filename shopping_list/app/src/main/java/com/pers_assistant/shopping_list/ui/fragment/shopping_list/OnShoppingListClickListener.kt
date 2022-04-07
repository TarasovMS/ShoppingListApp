package com.pers_assistant.shopping_list.ui.fragment.shopping_list

import com.pers_assistant.shopping_list.domain.entities.ShoppingList

interface OnShoppingListClickListener {
    fun clickedShoppingListItem(shoppingList: ShoppingList)
    fun deleteItem(shoppingList: ShoppingList)
    fun editItem(shoppingList: ShoppingList)
}