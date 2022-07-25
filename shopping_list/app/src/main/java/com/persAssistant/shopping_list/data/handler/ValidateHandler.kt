package com.persAssistant.shopping_list.data.handler

import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure

interface ValidateHandler {

    fun validationFields(value: String): ExecutionResult<Failure, String>

    fun validationEmail(value: String): ExecutionResult<Failure, String>

}
