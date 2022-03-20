package com.persAssistant.shopping_list.feature.splash.validation

import androidx.core.widget.doOnTextChanged
import androidx.viewbinding.ViewBinding
import com.persAssistant.shopping_list.error.*
import com.persAssistant.shopping_list.util.clearError

abstract class ValidationComponent<T : Any>(
    open val bindingComponent: BindingComponent<T>,
) : Component, UiEventListener, ValidationComponentFieldGetter {

    var allowEmpty = true
    lateinit var binding: ViewBinding

    abstract fun getErrorMessageResource(failure: Failure): Int

    abstract fun validationHandler(
        text: String,
        allowEmpty: Boolean = false
    ): ExecutionResult<RegistrationError, None>

    override fun initialize() {
        binding = bindingComponent.binding

        getValidationField().doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty())
                this.allowEmpty = allowEmpty()

            validateAndHandle(
                bindingComponent.getFieldValue().toString(),
                allowEmpty
            )
        }
    }

    open fun allowEmpty(): Boolean {
        return bindingComponent.allowEmpty()
    }

    open fun getValidationFieldValue(): String {
        return bindingComponent.getFieldValue().toString()
    }

    private fun onValidationSuccess() {
        binding.root.post {
            getValidationFieldInputLayout().isErrorEnabled = false
            getValidationFieldInputLayout().clearError()
        }
    }

    open fun onValidationError(failure: Failure, needRequestFocus: Boolean = true) {
        binding.root.post {
            getValidationFieldInputLayout().isErrorEnabled = true
            getValidationFieldInputLayout().error =
                bindingComponent.fragment.context?.getString(
                    getErrorMessageResource(
                        failure
                    )
                )

            if (needRequestFocus)
                getValidationFieldInputLayout().requestFocus()
        }
    }

    override suspend fun onRegistrationNextButtonClicked(): ExecutionResult<Failure, None> {
        allowEmpty = bindingComponent.allowEmpty()
        return when (val result = validate(getValidationFieldValue(), allowEmpty)) {
            is ExecutionResult.Fail -> {
                onValidationError(failure = result.failure)
                result
            }
            is ExecutionResult.Success -> {
                onValidationSuccess()
                result
            }
        }
    }

    open fun validate(
        text: String?,
        allowEmpty: Boolean = false,
    ): ExecutionResult<RegistrationError, None> {
        text ?: return ExecutionResult.Success(
            None()
        )
        if (text.isNullOrEmpty() && allowEmpty) return ExecutionResult.Success(
            None()
        )
        if (!binding.root.isEnabled){
            return None().toSuccess()
        }

        return validationHandler(text, allowEmpty)
    }

    fun validateAndHandle(
        text: String?,
        allowEmpty: Boolean = false,
        needRequestFocus: Boolean = true
    ) {
        validate(text, allowEmpty).either(
            functionError = { failure -> onValidationError(failure, needRequestFocus) },
            functionSuccess = { onValidationSuccess() }
        )
    }
}
