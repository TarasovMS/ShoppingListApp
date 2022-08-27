package com.persAssistant.shopping_list.feature.category

import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.feature.category.view_model.CategoryViewModel
import com.persAssistant.shopping_list.feature.category.view_model.CreatorCategoryViewModel
import javax.inject.Inject

class CreatorCategoryFragment : CategoryFragment() {

    @Inject
    lateinit var categoryInteract: CategoryInteractorImpl

    override fun createViewModel(): CategoryViewModel {
        return CreatorCategoryViewModel(categoryInteract)
    }
}
