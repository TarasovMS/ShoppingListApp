package com.pers_assistant.shopping_list.feature.validation

import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

interface ValidationComponentFieldGetter {
    fun getValidationField(): TextView
    fun getValidationFieldInputLayout(): TextInputLayout
}
