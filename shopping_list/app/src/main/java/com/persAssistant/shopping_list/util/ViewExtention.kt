package com.persAssistant.shopping_list.util

import android.view.View
import com.google.android.material.textfield.TextInputLayout

fun View.visibleWithOutFade() {
    visibility = View.VISIBLE
}

fun View.goneWithOutFade() {
    visibility = View.GONE
}

fun TextInputLayout.clearError() {
    error = null
}
