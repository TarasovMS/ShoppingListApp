package com.persAssistant.shopping_list.feature.user_help.handling.usecase

import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.RegistrationError
import com.persAssistant.shopping_list.util.validateFields
import javax.inject.Inject

class HandlingUseCase @Inject constructor() {

    fun validateName(name: String): ExecutionResult<Failure, String> {
        return if (name.validateFields())
            ExecutionResult.Success(name)
        else
            ExecutionResult.Fail(RegistrationError.NameValidationError)
    }

    fun validateMessage(message: String): ExecutionResult<Failure, String> {
        return if (message.validateFields())
            ExecutionResult.Success(message)
        else
            ExecutionResult.Fail(RegistrationError.MessageValidationError)
    }
}
