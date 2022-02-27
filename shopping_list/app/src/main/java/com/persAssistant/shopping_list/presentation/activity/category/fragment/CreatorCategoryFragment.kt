package com.persAssistant.shopping_list.presentation.activity.category.fragment

import com.persAssistant.shopping_list.presentation.App
import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel
import com.persAssistant.shopping_list.presentation.activity.category.CreatorCategoryViewModel

class CreatorCategoryFragment : CategoryFragment() {

    override fun createViewModel(): CategoryViewModel {
        val app = requireContext().applicationContext as App
        return CreatorCategoryViewModel(app.appComponent.getCategoryInteractor())
    }
}