package com.persAssistant.shopping_list.presentation.activity.category.fragment

import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.presentation.activity.category.CategoryViewModel
import com.persAssistant.shopping_list.presentation.activity.category.CreatorCategoryViewModel
import javax.inject.Inject

class CreatorCategoryFragment : CategoryFragment() {

    @Inject lateinit var categoryInteract: CategoryInteractor

    override fun createViewModel(): CategoryViewModel {
        return CreatorCategoryViewModel(categoryInteract)
    }
}