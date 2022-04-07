package com.pers_assistant.shopping_list.ui.fragment.category

import com.pers_assistant.shopping_list.domain.interactors.CategoryInteractor
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.CategoryViewModel
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.CreatorCategoryViewModel
import javax.inject.Inject

class CreatorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: CategoryInteractor

    override fun createViewModel(): CategoryViewModel {
        return CreatorCategoryViewModel(categoryInteract)
    }
}