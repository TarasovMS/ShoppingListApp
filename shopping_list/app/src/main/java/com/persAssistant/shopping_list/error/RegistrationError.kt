package com.persAssistant.shopping_list.error

import com.persAssistant.shopping_list.R


open class RegistrationError : Failure {

    open var throwable: Throwable? = null

    object EmptyField : RegistrationError()

    object EmailValidationError : RegistrationError() {
        override fun getErrorMessageResource() = R.string.email_incorrect
        object ValidationEmailError: RegistrationError()
    }

    object MessageValidationError : RegistrationError() {
        override fun getErrorMessageResource() = R.string.message_is_empty
        object ValidationMessageError: RegistrationError()
    }

    object NameValidationError : RegistrationError() {
        override fun getErrorMessageResource() = R.string.name_incorrect
        object ValidationNameError: RegistrationError()
    }

    object TitleValidationError : RegistrationError() {
        override fun getErrorMessageResource() = R.string.name_incorrect
        object ValidationTitleError: RegistrationError()
    }

}
