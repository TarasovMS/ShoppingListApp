package com.persAssistant.shopping_list.presentation.activity.category

import com.persAssistant.shopping_list.presentation.App

class CreatorCategoryActivity : CategoryActivity() {

    override fun createViewModel(): CategoryViewModel {
        val app = applicationContext as App
        return CreatorCategoryViewModel(app.appComponent.getCategoryInteractor())
    }
}