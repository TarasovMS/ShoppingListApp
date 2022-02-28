package com.persAssistant.shopping_list.presentation.activity.category.fragment

import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel
import com.persAssistant.shopping_list.presentation.activity.category.EditorCategoryViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: EditorCategoryViewModel

    override fun createViewModel(): CategoryViewModel {
        val id = arguments
            ?.getLong(KEY_CATEGORY)
            ?: throw Exception("Ошибка в EditorCategoryActivity.getIntent отсутствует Id")

        categoryInteract.init(id)

        return categoryInteract
    }
}