package com.persAssistant.shopping_list.feature.user_help.handling.ui

import androidx.annotation.StringRes


interface HandlingView {

    fun tittleError(message: String)
    fun tittleError(@StringRes id: Int)

    fun messageError(message: String)
    fun messageError(@StringRes id: Int)
}
