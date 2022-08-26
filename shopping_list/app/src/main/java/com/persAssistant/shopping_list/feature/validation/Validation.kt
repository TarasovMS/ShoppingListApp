//package com.persAssistant.shopping_list.feature.validation
//
//import com.persAssistant.shopping_list.error.*
//import com.persAssistant.shopping_list.util.clearError
//
//abstract class Validation<T : Any>(
////    open val textViewComponent: TextView<T>,
//) : ValidationComponentFieldGetter {
//
////    lateinit var binding: ViewBinding
//
//    abstract fun getErrorMessageResource(failure: Failure): Int
//
//    abstract fun validationHandler(
//        text: String,
//        allowEmpty: Boolean = false
//    ): ExecutionResult<RegistrationError, None>
//
//    open fun getValidationFieldValue(): String {
//        return textViewComponent.getFieldValue().toString()
//    }
//
//    private fun onValidationSuccess() {
////        binding.root.post {
//            getValidationFieldInputLayout().isErrorEnabled = false
//            getValidationFieldInputLayout().clearError()
////        }
//    }
//
//    open fun onValidationError(failure: Failure, needRequestFocus: Boolean = true) {
////        binding.root.post {
//            getValidationFieldInputLayout().isErrorEnabled = true
//            getValidationFieldInputLayout().error =
//
//                textViewComponent.moduleFragment.context?.getString(
//                    getErrorMessageResource(
//                        failure
//                    )
//                )
//
//            if (needRequestFocus)
//                getValidationFieldInputLayout().requestFocus()
////        }
//    }
//
//    open fun validate(
//        text: String?,
//        allowEmpty: Boolean = false,
//    ): ExecutionResult<RegistrationError, None> {
//
//        text ?: return ExecutionResult.Success(None())
//
//        if (text.isNullOrEmpty() && allowEmpty) return ExecutionResult.Success(None())
//
//        return validationHandler(text, allowEmpty)
//    }
//
//    fun validateAndHandle(
//        text: String?,
//        allowEmpty: Boolean = false,
//        needRequestFocus: Boolean = true
//    ) {
//        validate(text, allowEmpty).either(
//            functionLeft = { failure -> onValidationError(failure, needRequestFocus) },
//            functionRight = { onValidationSuccess() }
//        )
//    }
//
//}
