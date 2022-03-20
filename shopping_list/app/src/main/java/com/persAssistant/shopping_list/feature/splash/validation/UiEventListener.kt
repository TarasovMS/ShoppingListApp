package com.persAssistant.shopping_list.feature.splash.validation

import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.None

interface UiEventListener {

    suspend fun onRegistrationNextButtonClicked(): ExecutionResult<Failure, None>
}
