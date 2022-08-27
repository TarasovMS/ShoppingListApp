package com.persAssistant.shopping_list.feature.category

import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.feature.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.feature.category.view_model.EditorCategoryViewModel
import java.lang.Exception
import javax.inject.Inject

class EditorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: EditorCategoryViewModel

    override fun createViewModel(): CategoryViewModel {
        val id = arguments?.getLong(KEY_CATEGORY)
            ?: throw Exception(getString(R.string.error_id_in_editor_category_activity))

        categoryInteract.init(id)

        return categoryInteract
    }
}
