package com.persAssistant.shopping_list.feature.category.adapter

import com.persAssistant.shopping_list.domain.entities.Category

interface OnCategoryClickListener {

    fun clickedCategoryItem(category: Category)

    fun deleteItem(category: Category)

    fun editItem(category: Category)
}
