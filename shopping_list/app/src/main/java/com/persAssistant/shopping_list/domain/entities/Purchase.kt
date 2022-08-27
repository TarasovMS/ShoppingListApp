package com.persAssistant.shopping_list.domain.entities

import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.QUANTITY_DEFAULT_ONE_STRING
import com.persAssistant.shopping_list.common.ZERO_POSITION

data class Purchase(
    var id: Long? = null,
    var name: String = EMPTY_STRING,
    var categoryId: Long = DEFAULT_CATEGORIES_COUNT,
    var listId: Long = DEFAULT_INVALID_ID,
    var price: Double = PRICE_DEFAULT_DOUBLE,
    var quantity: String = QUANTITY_DEFAULT_ONE_STRING,
    var unit: String = EMPTY_STRING,
    var isCompleted: Int = ZERO_POSITION
)
