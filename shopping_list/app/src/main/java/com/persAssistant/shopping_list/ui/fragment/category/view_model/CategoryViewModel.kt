package com.persAssistant.shopping_list.ui.fragment.category.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.base.AppBaseViewModel

abstract class CategoryViewModel : AppBaseViewModel() {

    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()

    abstract fun save()
}
