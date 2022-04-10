package com.pers_assistant.shopping_list.feature.validation

import com.pers_assistant.shopping_list.error.ExecutionResult
import com.pers_assistant.shopping_list.error.Failure
import com.pers_assistant.shopping_list.error.None

interface UiEventListener {

    suspend fun onRegistrationNextButtonClicked(): ExecutionResult<Failure, None>
}
