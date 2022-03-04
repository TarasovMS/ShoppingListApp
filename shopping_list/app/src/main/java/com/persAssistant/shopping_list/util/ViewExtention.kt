package com.persAssistant.shopping_list.util

import android.view.View

fun View.visibleWithOutFade() {
    visibility = View.VISIBLE
}

fun View.goneWithOutFade() {
    visibility = View.GONE
}