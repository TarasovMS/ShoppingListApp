package com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment

import com.persAssistant.shopping_list.domain.entities.Category

interface OnCategoryClickListener {
    fun clickedCategoryItem(category: Category)
    fun deleteItem(category: Category)
    fun editItem(category: Category)
}