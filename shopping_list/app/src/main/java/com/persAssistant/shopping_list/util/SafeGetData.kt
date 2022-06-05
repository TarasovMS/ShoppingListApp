package com.persAssistant.shopping_list.util

import android.util.Log

fun safeGetData(block: () -> Unit){
    try {
        block.invoke()
    }catch (e: Exception){
        Log.e(ERROR,e.toString())
    }
}
