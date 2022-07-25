package com.persAssistant.shopping_list.data.handler

import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.RegistrationError
import com.persAssistant.shopping_list.util.validateEmail
import com.persAssistant.shopping_list.util.validateFields
import javax.inject.Inject

class ValidateHandlerImpl @Inject constructor(): ValidateHandler {

    override fun validationFields(value: String): ExecutionResult<Failure, String> {
        return if (value.validateFields())
            ExecutionResult.Success(value)
        else
            ExecutionResult.Fail(RegistrationError.MessageValidationError)
    }

    override fun validationEmail(value: String): ExecutionResult<Failure, String> {
        return if (value.validateEmail())
            ExecutionResult.Success(value)
        else
            ExecutionResult.Fail(RegistrationError.MessageValidationError)
    }

}
