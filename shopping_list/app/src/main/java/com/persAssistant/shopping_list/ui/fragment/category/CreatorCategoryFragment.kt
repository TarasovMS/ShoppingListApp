package com.persAssistant.shopping_list.ui.fragment.category

import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.ui.fragment.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.ui.fragment.category.view_model.CreatorCategoryViewModel
import javax.inject.Inject

class CreatorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: CategoryInteractor

    override fun createViewModel(): CategoryViewModel {
        return CreatorCategoryViewModel(categoryInteract)
    }
}
