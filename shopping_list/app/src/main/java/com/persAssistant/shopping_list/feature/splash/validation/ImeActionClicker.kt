package com.persAssistant.shopping_list.feature.splash.validation

import android.widget.AutoCompleteTextView

interface ImeActionClicker {

    fun getFocusView(): AutoCompleteTextView
    fun fieldType(): String
}
