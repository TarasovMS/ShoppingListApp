package com.pers_assistant.shopping_list.feature.user_help.handling.usecase

import com.pers_assistant.shopping_list.error.ExecutionResult
import com.pers_assistant.shopping_list.error.Failure
import com.pers_assistant.shopping_list.error.RegistrationError
import com.pers_assistant.shopping_list.util.validateFields
import javax.inject.Inject

class HandlingUseCase @Inject constructor() {

    fun validateTitle(title: String): ExecutionResult<Failure, String> {
        return if (title.validateFields())
            ExecutionResult.Success(title)
        else
            ExecutionResult.Fail(RegistrationError.TitleValidationError)
    }

    fun validateMessage(message: String): ExecutionResult<Failure, String> {
        return if (message.validateFields())
            ExecutionResult.Success(message)
        else
            ExecutionResult.Fail(RegistrationError.MessageValidationError)
    }
}
