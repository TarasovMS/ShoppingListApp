package com.pers_assistant.shopping_list.ui.fragment.category

import com.pers_assistant.shopping_list.ui.fragment.category.view_model.CategoryViewModel
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.EditorCategoryViewModel
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