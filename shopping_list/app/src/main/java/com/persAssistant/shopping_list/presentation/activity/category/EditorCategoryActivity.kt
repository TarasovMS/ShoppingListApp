package com.persAssistant.shopping_list.presentation.activity.category

import com.persAssistant.shopping_list.presentation.App
import java.lang.Exception

class EditorCategoryActivity : CategoryActivity() {

    override fun createViewModel(): CategoryViewModel {
        val app = applicationContext as App
        val id = intent.getLongExtra(KEY_CATEGORY,-1L)
        if(id == -1L)
            throw Exception("Ошибка в EditorCategoryActivity.getIntent отсутствует Id")
        val viewModel = app.appComponent.getEditorCategoryViewModel()
        viewModel.init(id)
        return viewModel
    }
}