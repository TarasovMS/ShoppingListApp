package com.persAssistant.shopping_list.error

import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

interface ViewError {
    fun showFieldError(@StringRes id: Int, fieldView: View, parentLayout: TextInputLayout? = null)
    fun showTextInputFieldError(input: EditText, parentLayout: TextInputLayout?)

    // чтобы был на всякий
//    fun focusOnView(unusedMessage: String? = null, fieldView: View)
}