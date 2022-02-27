package com.persAssistant.shopping_list.presentation.activity.category.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel
import java.lang.Exception

class EditorCategoryFragment : CategoryFragment() {

    override fun createViewModel(): CategoryViewModel {
        val app = requireContext().applicationContext as App
        val id = arguments?.getLong(KEY_CATEGORY)
        if(id == null)
            throw Exception("Ошибка в EditorCategoryActivity.getIntent отсутствует Id")
        val viewModel = app.appComponent.getEditorCategoryViewModel()
        viewModel.init(id)
        return viewModel
    }
}