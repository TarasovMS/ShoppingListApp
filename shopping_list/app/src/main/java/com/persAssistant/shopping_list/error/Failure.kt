package com.persAssistant.shopping_list.error

import androidx.annotation.StringRes
import com.persAssistant.shopping_list.R

interface Failure {

    @StringRes
    fun getErrorMessageResource(): Int = R.string.unknown_error_message

}
