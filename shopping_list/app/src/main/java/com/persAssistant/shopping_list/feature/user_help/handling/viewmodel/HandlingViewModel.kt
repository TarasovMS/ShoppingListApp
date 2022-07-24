package com.persAssistant.shopping_list.feature.user_help.handling.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.persAssistant.shopping_list.common.AppBaseViewModel
import com.persAssistant.shopping_list.error.Failure
import com.persAssistant.shopping_list.feature.user_help.handling.model.Handling
import com.persAssistant.shopping_list.feature.user_help.handling.usecase.HandlingUseCase
import javax.inject.Inject

class HandlingViewModel @Inject constructor(
    private val useCase: HandlingUseCase
) : AppBaseViewModel() {

    enum class FieldValidation {
        TITLE,
        MESSAGE,
        ALL_FIELDS;

        fun isTitle() = this == TITLE
        fun isMessage() = this == MESSAGE
        fun isAllFields() = this == ALL_FIELDS
    }

    val isActionEnabled = MutableLiveData(false)
    val handling: MutableLiveData<Handling> = MutableLiveData()
    val errorMessage: MutableLiveData<Failure> = MutableLiveData()
    val errorTitle: MutableLiveData<Failure> = MutableLiveData()

    fun validateInputs(
        field: FieldValidation?,
        title: String,
        message: String
    ) {
        when (field) {
            FieldValidation.TITLE -> validateTitle(title = title)
            FieldValidation.MESSAGE -> validateMessage(message = message)
            else -> validateData(title = title, message = message)
        }
    }

    private fun validateData(title: String, message: String) {
        isActionEnabled.postValue(title.isNotBlank() && message.isNotBlank())
        validateTitle(title)
        validateMessage(message)
    }

    private fun validateTitle(title: String) {
        useCase.validateName(title)
            .either(
                functionError = {
                    handleFailure(it)
                    errorTitle.postValue(it)
                    isActionEnabled.postValue(false)
                },

                functionSuccess = {
                    Log.d("validateTitleSuccess", it)
                }
            )
    }

    private fun validateMessage(message: String) {
        useCase.validateMessage(message)
            .either(
                functionError = {
                    handleFailure(it)
                    errorMessage.postValue(it)
                    isActionEnabled.postValue(false)
                },

                functionSuccess = {
                    Log.d("validateMessageSuccess", it)
                }
            )
    }
}
