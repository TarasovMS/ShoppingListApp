package com.persAssistant.shopping_list.common.validation_handler

import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure

interface ValidateHandler {

    fun validationFields(value: String): ExecutionResult<Failure, String>

    fun validationEmail(value: String): ExecutionResult<Failure, String>

    fun validationName(value: String): ExecutionResult<Failure, String>

}
