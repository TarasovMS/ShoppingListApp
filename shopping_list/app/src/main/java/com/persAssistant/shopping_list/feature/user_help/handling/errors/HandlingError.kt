package com.persAssistant.shopping_list.feature.user_help.handling.errors

import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.error.Failure

object HandlingError : Failure {
    override fun getErrorMessageResource() = R.string.handling_error
}
