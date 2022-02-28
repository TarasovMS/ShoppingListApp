package com.persAssistant.shopping_list.presentation.fragment.category.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class CategoryViewModel : ViewModel() {

    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()

    abstract fun save()
}