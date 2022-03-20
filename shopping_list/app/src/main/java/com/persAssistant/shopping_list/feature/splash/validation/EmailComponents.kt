package com.persAssistant.shopping_list.feature.splash.validation

import android.text.InputType
import android.util.Patterns
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.TextInputFragmentBinding
import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.None
import com.persAssistant.shopping_list.error.RegistrationError

object EmailComponents : ComponentsContainer {

    override val componentType: String
        get() = "email"

    override fun getComponents(fragment: AppBaseFragment): ArrayList<Component> {
        val bindingComponent = EmailBindingComponent(fragment)
        val validationComponent = EmailValidationComponent(bindingComponent)

        return arrayListOf(
            validationComponent,
        )
    }

    class EmailBindingComponent(
        override val fragment: AppBaseFragment
    ) : DaDataBindingComponent<String>(fragment, R.string.email_hint) {

        override fun initialize() {
            super.initialize()
            (binding as TextInputFragmentBinding).inputValue.apply {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                inputValue = this
            }
        }

        override fun getFieldValue(): String {
            return (binding as TextInputFragmentBinding).inputValue.text.toString()
        }

        override fun setFieldValue(value: String?) {
            (binding as TextInputFragmentBinding).inputValue.setText(value)
        }
    }

    class EmailValidationComponent(override val bindingComponent: EmailBindingComponent) :
        ValidationComponent<String>(bindingComponent) {

        override fun getValidationField(): TextView {
            return (binding as TextInputFragmentBinding).inputValue
        }

        override fun getValidationFieldInputLayout(): TextInputLayout {
            return (binding as TextInputFragmentBinding).inputLayout
        }

        override fun getErrorMessageResource(failure: Failure): Int {
            return when (failure) {
                RegistrationError.EmailValidationError.ValidationEmailError ->
                    R.string.email_validation_incorrect

                else -> R.string.email_incorrect
            }
        }

        override fun validationHandler(
            text: String,
            allowEmpty: Boolean
        ): ExecutionResult<RegistrationError, None> {
            if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                return ExecutionResult.Fail(RegistrationError.EmailValidationError.ValidationEmailError)
            }

            return ExecutionResult.Success(None())
        }
    }
}
