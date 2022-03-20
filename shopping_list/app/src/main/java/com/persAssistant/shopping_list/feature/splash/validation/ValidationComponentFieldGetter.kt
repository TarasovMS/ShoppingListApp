package com.persAssistant.shopping_list.feature.splash.validation

import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

interface ValidationComponentFieldGetter {
    fun getValidationField(): TextView
    fun getValidationFieldInputLayout(): TextInputLayout
}
