package com.persAssistant.shopping_list.feature.category.fragments

import androidx.navigation.fragment.navArgs
import com.persAssistant.shopping_list.feature.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.feature.category.view_model.EditorCategoryViewModel
import javax.inject.Inject

class EditorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: EditorCategoryViewModel

    private val args: EditorCategoryFragmentArgs by navArgs()

    override fun createViewModel(): CategoryViewModel {
        categoryInteract.init(args.categoryId)
        return categoryInteract
    }

}
