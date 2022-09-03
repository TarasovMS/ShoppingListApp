package com.persAssistant.shopping_list.feature.shopping_list.view_model

import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.common.DATE_FORMAT
import com.persAssistant.shopping_list.common.DEFAULT_DATE_FORMAT
import com.persAssistant.shopping_list.common.EMPTY_STRING
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class ShoppingListViewModel @Inject constructor() : AppBaseViewModel() {
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData(EMPTY_STRING)
    var strDate = MutableLiveData(DEFAULT_DATE_FORMAT)

    var date = Date()
        set(value) {
            field = value
            strDate.value = SimpleDateFormat(DATE_FORMAT, Locale.US).format(date)
        }

    init {
        date = Date()
    }

    open fun save() {}
}
