package com.pers_assistant.shopping_list.feature.validation

import android.text.InputType
import android.util.Patterns
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.pers_assistant.shopping_list.R
import com.pers_assistant.shopping_list.base.AppBaseFragment
import com.pers_assistant.shopping_list.databinding.TextInputFragmentBinding
import com.pers_assistant.shopping_list.error.ExecutionResult
import com.pers_assistant.shopping_list.error.Failure
import com.pers_assistant.shopping_list.error.None
import com.pers_assistant.shopping_list.error.RegistrationError
import com.pers_assistant.shopping_list.error.RegistrationError.EmailValidationError.ValidationEmailError

object MessageComponents : ComponentsContainer {

    override val componentType: String
        get() = "message"

    override fun getComponents(fragment: AppBaseFragment): ArrayList<Component> {
        val bindingComponent = MessageBindingComponent(fragment)
        val validationComponent = MessageValidationComponent(bindingComponent)

        return arrayListOf(
            bindingComponent,
            validationComponent
        )
    }

    class MessageBindingComponent(
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


    class MessageValidationComponent(override val bindingComponent: MessageBindingComponent) :
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
