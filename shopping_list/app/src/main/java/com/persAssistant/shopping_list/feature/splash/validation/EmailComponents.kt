package com.persAssistant.shopping_list.feature.splash.validation

import android.text.InputType
import android.util.Patterns
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.TextInputFragmentBinding
import com.persAssistant.shopping_list.error.ExecutionResult
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.error.None
import com.persAssistant.shopping_list.error.RegistrationError
import com.persAssistant.shopping_list.error.RegistrationError.EmailValidationError.ValidationEmailError

object EmailComponents : ComponentsContainer {

    override val componentType: String
        get() = "email"

    override fun getComponents(fragment: AppBaseFragment): ArrayList<Component> {
        val bindingComponent = EmailBindingComponent(fragment)
        val validationComponent = EmailValidationComponent(bindingComponent)

        return arrayListOf(
            bindingComponent,
            validationComponent
        )
    }

    class EmailBindingComponent(
        override val fragment: AppBaseFragment
    ) : StringBindingComponent(fragment, R.string.email_hint) {

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

        override fun getValidationField(): AutoCompleteTextView {
            return (binding as TextInputFragmentBinding).inputValue
        }

        override fun getValidationFieldInputLayout(): TextInputLayout {
            return (binding as TextInputFragmentBinding).inputLayout
        }

        override fun getErrorMessageResource(failure: Failure): Int {
            return when (failure) {
                ValidationEmailError -> R.string.email_validation_incorrect
                else -> R.string.email_incorrect
            }
        }

        override fun validationHandler(
            text: String,
            allowEmpty: Boolean
        ): ExecutionResult<RegistrationError, None> {
            if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                return ExecutionResult.Fail(ValidationEmailError)
            }

            return ExecutionResult.Success(None())
        }
    }
}
