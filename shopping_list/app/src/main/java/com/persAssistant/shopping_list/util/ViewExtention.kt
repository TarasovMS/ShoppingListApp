package com.persAssistant.shopping_list.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

fun View.hideKeyboard() {
    ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())
}