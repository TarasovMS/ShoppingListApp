package com.persAssistant.shopping_list.ui.fragment.shopping_list.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.common.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class ShoppingListViewModel @Inject constructor() : AppBaseViewModel() {
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var strDate = MutableLiveData<String>()

    var date = Date()
        set(value) {
            field = value
            strDate.value = SimpleDateFormat(DATE_FORMAT).format(date)
        }

    init {
        date = Date()
    }

    open fun save() {}
}
