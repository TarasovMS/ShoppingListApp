package com.pers_assistant.shopping_list.ui.fragment.category

import com.pers_assistant.shopping_list.domain.entities.Category

interface OnCategoryClickListener {
    fun clickedCategoryItem(category: Category)
    fun deleteItem(category: Category)
    fun editItem(category: Category)
}