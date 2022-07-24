package com.persAssistant.shopping_list.feature.user_help.handling.usecase

import com.persAssistant.shopping_list.common.handler.ValidateHandler
import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import javax.inject.Inject

class HandlingUseCase @Inject constructor(
    private val validateHandler: ValidateHandler
) {

    fun validateName(name: String): ExecutionResult<Failure, String> {
        return validateHandler.validationFields(name)
    }

    fun validateMessage(message: String): ExecutionResult<Failure, String> {
        return validateHandler.validationFields(message)
    }

}
