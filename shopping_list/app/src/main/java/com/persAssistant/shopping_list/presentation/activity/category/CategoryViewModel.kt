package com.persAssistant.shopping_list.presentation.activity.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class CategoryViewModel @Inject constructor() : ViewModel() {

    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()

    open fun save() {}
}