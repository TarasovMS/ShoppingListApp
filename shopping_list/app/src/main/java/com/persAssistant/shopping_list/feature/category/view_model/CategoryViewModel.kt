package com.persAssistant.shopping_list.feature.category.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel

abstract class CategoryViewModel : AppBaseViewModel() {
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()

    abstract fun save()
}
