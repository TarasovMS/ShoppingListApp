package com.pers_assistant.shopping_list.feature.user_help.handling.errors

import com.pers_assistant.shopping_list.R
import com.pers_assistant.shopping_list.error.Failure

object HandlingError : Failure {
    override fun getErrorMessageResource() = R.string.handling_error
}
