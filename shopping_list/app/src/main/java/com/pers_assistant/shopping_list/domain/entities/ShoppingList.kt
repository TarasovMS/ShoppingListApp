package com.pers_assistant.shopping_list.domain.entities

import java.util.*

data class ShoppingList(
    var id: Long? = null,
    var date: Date,
    var name: String
)

