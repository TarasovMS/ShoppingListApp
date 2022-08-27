package com.persAssistant.shopping_list.util

import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.updateStatusBarColor(@ColorRes color: Int) {
    activity?.window?.apply {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        context?.let {
            statusBarColor = ContextCompat.getColor(it, color)
        }
    }
}

fun <T> ArrayAdapter<T>.updateAdapter(list: List<T>) {
    clear()
    addAll(list)
    notifyDataSetChanged()
}

fun Int?.getOrSet(value: Int) = this ?: value
fun Long?.getOrSet(value: Long) = this ?: value
fun Float?.getOrSet(value: Float) = this ?: value
fun Double?.getOrSet(value: Double) = this ?: value
fun String?.getOrSet(value: String) = this ?: value
fun Boolean?.getOrSet(value: Boolean) = this ?: value

