package com.persAssistant.shopping_list.util

import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearError() {
    error = null
}

fun <T> ArrayAdapter<T>.updateAdapter(list: List<T>) {
    clear()
    addAll(list)
    notifyDataSetChanged()
}

fun View.hideKeyboard() {
    ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Fragment.updateStatusBarColor(@ColorRes color: Int) {
    activity?.window?.apply {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        context?.let {
            statusBarColor = ContextCompat.getColor(it, color)
        }
    }
}
