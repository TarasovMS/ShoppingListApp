package com.persAssistant.shopping_list.presentation.activity.shopping_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class ShoppingListViewModel @Inject constructor() : ViewModel() {
    var closeEvent = MutableLiveData<Unit>()
    var name = MutableLiveData<String>()
    var strDate = MutableLiveData<String>()
    var date = Date()
    set(value) {
        field = value
        strDate.value = SimpleDateFormat("dd.MM.yyyy").format(date)
    }

    init { date = Date() }

    open fun save() {}
}