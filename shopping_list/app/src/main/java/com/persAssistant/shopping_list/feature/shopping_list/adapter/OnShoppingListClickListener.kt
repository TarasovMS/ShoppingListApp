package com.persAssistant.shopping_list.feature.shopping_list.adapter

import com.persAssistant.shopping_list.domain.entities.ShoppingList

interface OnShoppingListClickListener {

    fun clickItem(shoppingList: ShoppingList)

    fun deleteItem(shoppingList: ShoppingList)

    fun editItem(shoppingList: ShoppingList)

}
